package com.ironsoftware.orderservice.service;

import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.common.events.order.OrderItemEvent;
import com.ironsoftware.orderservice.dto.OrderDto;
import com.ironsoftware.orderservice.dto.OrderItemDto;
import com.ironsoftware.orderservice.entity.Order;
import com.ironsoftware.orderservice.entity.OrderItem;
import com.ironsoftware.orderservice.entity.OrderStatus;
import com.ironsoftware.orderservice.repository.OrderRepository;
import com.ironsoftware.orderservice.client.InventoryServiceClient;
import com.ironsoftware.orderservice.client.ProductServiceClient;
import com.ironsoftware.orderservice.client.UserServiceClient;
import com.ironsoftware.orderservice.client.PaymentServiceClient;
import com.ironsoftware.orderservice.dto.PaymentRequest;
import com.ironsoftware.orderservice.dto.PaymentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;
    private final InventoryServiceClient inventoryServiceClient;
    private final ProductServiceClient productServiceClient;
    private final UserServiceClient userServiceClient;
    private final PaymentServiceClient paymentServiceClient;

    public List<OrderDto> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        // Step 1: Validate user exists
        if (!userServiceClient.userExists(orderDto.getUserId())) {
            throw new RuntimeException("User not found: " + orderDto.getUserId());
        }

        // Step 2: Validate inventory and reserve stock
        for (OrderItemDto item : orderDto.getItems()) {
            if (!inventoryServiceClient.checkAvailability(item.getProductId(), item.getQuantity())) {
                throw new RuntimeException("Insufficient inventory for product: " + item.getProductId());
            }
        }

        // Step 3: Reserve inventory for all items
        try {
            for (OrderItemDto item : orderDto.getItems()) {
                inventoryServiceClient.reserveStock(item.getProductId(), item.getQuantity());
            }
        } catch (Exception e) {
            // Rollback any reservations made
            rollbackInventoryReservations(orderDto.getItems());
            throw new RuntimeException("Failed to reserve inventory: " + e.getMessage());
        }

        // Step 4: Create order with PENDING status
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setStatus(OrderStatus.PENDING); // Changed from CREATED to PENDING

        List<OrderItem> orderItems = orderDto.getItems().stream()
                .map(item -> mapToOrderItemWithProductDetails(item, order))
                .collect(Collectors.toList());

        // Add items to order (this will trigger recalculation)
        orderItems.forEach(order::addItem);
        
        final Order savedOrder = orderRepository.save(order);

        // Publish order created event with real data
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setEventType("ORDER_CREATED");
        event.setTimestamp(Instant.now());
        event.setSource("order-service");
        event.setVersion("1.0");
        
        event.setOrderId(savedOrder.getId().toString());
        event.setUserId(savedOrder.getUserId());
        event.setTotalAmount(savedOrder.getTotal());
        event.setShippingAddress(orderDto.getShippingAddress() != null ? orderDto.getShippingAddress() : "Default Address");
        event.setPaymentMethodId(orderDto.getPaymentMethodId() != null ? orderDto.getPaymentMethodId() : "default-payment-method");
        
        // Convert order items with actual product data
        List<OrderItemEvent> eventItems = savedOrder.getItems().stream()
            .map(item -> {
                OrderItemEvent eventItem = new OrderItemEvent();
                eventItem.setProductId(item.getProductId());
                eventItem.setProductName(item.getProductName());
                eventItem.setQuantity(item.getQuantity());
                eventItem.setPrice(item.getPrice());
                eventItem.setVariantId(item.getVariantId() != null ? item.getVariantId() : "default-variant");
                return eventItem;
            })
            .collect(Collectors.toList());
        
        event.setItems(eventItems);
        
        kafkaTemplate.send("order-created", event);

        return mapToDto(savedOrder);
    }
    
    private void publishOrderEvent(Order order, String eventType, String topic) {
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setEventType(eventType);
        event.setTimestamp(Instant.now());
        event.setSource("order-service");
        event.setVersion("1.0");
        
        event.setOrderId(order.getId().toString());
        event.setUserId(order.getUserId());
        event.setTotalAmount(order.getTotal());
        
        List<OrderItemEvent> eventItems = order.getItems().stream()
            .map(item -> {
                OrderItemEvent eventItem = new OrderItemEvent();
                eventItem.setProductId(item.getProductId());
                eventItem.setProductName(item.getProductName());
                eventItem.setQuantity(item.getQuantity());
                eventItem.setPrice(item.getPrice());
                eventItem.setVariantId(item.getVariantId() != null ? item.getVariantId() : "default-variant");
                return eventItem;
            })
            .collect(Collectors.toList());
        
        event.setItems(eventItems);
        kafkaTemplate.send(topic, event);
        
        log.info("Published {} event for order: {}", eventType, order.getId());
    }

    private OrderItem mapToOrderItemWithProductDetails(OrderItemDto itemDto, Order order) {
        // Fetch product details from product service
        var productDetails = productServiceClient.getProduct(itemDto.getProductId());
        
        return OrderItem.builder()
                .order(order)
                .productId(itemDto.getProductId())
                .productName(productDetails.getName())
                .quantity(itemDto.getQuantity())
                .price(productDetails.getPrice()) // Use current price from product service
                .subtotal(productDetails.getPrice().multiply(BigDecimal.valueOf(itemDto.getQuantity())))
                .variantId(itemDto.getVariantId())
                .build();
    }

    private void rollbackInventoryReservations(List<OrderItemDto> items) {
        for (OrderItemDto item : items) {
            try {
                inventoryServiceClient.releaseReservation(item.getProductId(), item.getQuantity());
            } catch (Exception e) {
                // Log error but continue with other rollbacks
                log.error("Failed to rollback inventory reservation for product: {}", item.getProductId(), e);
            }
        }
    }

    @Transactional
    public OrderDto confirmOrder(String orderId, String paymentMethodId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        
        if (order.getStatus() != OrderStatus.PENDING) {
            throw new RuntimeException("Order cannot be confirmed. Current status: " + order.getStatus());
        }
        
        // Process payment before confirming order
        PaymentRequest paymentRequest = PaymentRequest.builder()
                .orderId(orderId)
                .userId(order.getUserId())
                .amount(order.getTotal())
                .paymentMethod("CREDIT_CARD")
                .paymentMethodId(paymentMethodId)
                .build();
        
        try {
            PaymentResponse paymentResponse = paymentServiceClient.processPayment(paymentRequest);
            
            if (!"COMPLETED".equals(paymentResponse.getStatus())) {
                throw new RuntimeException("Payment failed: " + paymentResponse.getErrorMessage());
            }
            
            // Payment successful, confirm order
            order.setStatus(OrderStatus.CONFIRMED);
            Order savedOrder = orderRepository.save(order);
            
            // Publish order confirmed event to update inventory
            publishOrderEvent(savedOrder, "ORDER_CONFIRMED", "order-confirmed");
            
            log.info("Order {} confirmed with payment {}", orderId, paymentResponse.getTransactionId());
            return mapToDto(savedOrder);
            
        } catch (Exception e) {
            log.error("Failed to process payment for order: {}", orderId, e);
            throw new RuntimeException("Order confirmation failed due to payment error: " + e.getMessage());
        }
    }

    @Transactional
    public OrderDto cancelOrder(String orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
        
        if (order.getStatus() == OrderStatus.COMPLETED || order.getStatus() == OrderStatus.CANCELLED) {
            throw new RuntimeException("Order cannot be cancelled. Current status: " + order.getStatus());
        }
        
        order.setStatus(OrderStatus.CANCELLED);
        Order savedOrder = orderRepository.save(order);
        
        // Publish order cancelled event to release inventory reservations
        publishOrderEvent(savedOrder, "ORDER_CANCELLED", "order-cancelled");
        
        return mapToDto(savedOrder);
    }

    public Optional<OrderDto> findById(String orderId) {
        return orderRepository.findById(orderId)
                .map(this::mapToDto);
    }

    private OrderDto mapToDto(Order order) {
        List<OrderItemDto> itemDtos = order.getItems().stream()
                .map(this::mapToItemDto)
                .collect(Collectors.toList());

        return OrderDto.builder()
                .id(order.getId())
                .userId(order.getUserId())
                .totalAmount(order.getTotal())
                .status(order.getStatus())
                .createdAt(order.getCreatedAt())
                .items(itemDtos)
                .build();
    }

    private OrderItemDto mapToItemDto(OrderItem item) {
        return OrderItemDto.builder()
                .id(item.getId())
                .productId(item.getProductId())
                .quantity(item.getQuantity())
                .price(item.getPrice())
                .subtotal(item.getSubtotal())
                .build();
    }
}
