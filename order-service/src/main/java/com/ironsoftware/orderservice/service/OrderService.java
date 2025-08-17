package com.ironsoftware.orderservice.service;

import com.ironsoftware.common.events.order.OrderCreatedEvent;
import com.ironsoftware.common.events.order.OrderItemEvent;
import com.ironsoftware.orderservice.dto.OrderDto;
import com.ironsoftware.orderservice.dto.OrderItemDto;
import com.ironsoftware.orderservice.entity.Order;
import com.ironsoftware.orderservice.entity.OrderItem;
import com.ironsoftware.orderservice.entity.OrderStatus;
import com.ironsoftware.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public List<OrderDto> getUserOrders(String userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();
        order.setUserId(orderDto.getUserId());
        order.setSubtotal(orderDto.getTotalAmount());
        order.setTax(BigDecimal.ZERO); // Default tax
        order.setShippingCost(BigDecimal.ZERO); // Default shipping
        order.setTotal(orderDto.getTotalAmount());
        order.setStatus(OrderStatus.CREATED);

        List<OrderItem> orderItems = orderDto.getItems().stream()
                .map(item -> mapToOrderItem(item, order))
                .collect(Collectors.toList());

        // Add items to order (this will trigger recalculation)
        orderItems.forEach(order::addItem);
        
        final Order savedOrder = orderRepository.save(order);

        // Publish order created event
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setEventId(UUID.randomUUID().toString());
        event.setEventType("ORDER_CREATED");
        event.setTimestamp(Instant.now());
        event.setSource("order-service");
        event.setVersion("1.0");
        
        event.setOrderId(savedOrder.getId().toString());
        event.setUserId(savedOrder.getUserId());
        event.setTotalAmount(savedOrder.getTotal());
        event.setShippingAddress("Default Address"); // This should be populated from actual order data
        event.setPaymentMethodId("default-payment-method"); // This should be populated from actual order data
        
        // Convert order items
        List<OrderItemEvent> eventItems = orderDto.getItems().stream()
            .map(item -> {
                OrderItemEvent eventItem = new OrderItemEvent();
                eventItem.setProductId(item.getProductId());
                eventItem.setProductName("Product Name"); // This should be populated from product service
                eventItem.setQuantity(item.getQuantity());
                eventItem.setPrice(item.getPrice());
                eventItem.setVariantId("default-variant");
                return eventItem;
            })
            .collect(Collectors.toList());
        
        event.setItems(eventItems);
        
        kafkaTemplate.send("order-created", event);

        return mapToDto(savedOrder);
    }

    private OrderItem mapToOrderItem(OrderItemDto itemDto, Order order) {
        return OrderItem.builder()
                .order(order)
                .productId(itemDto.getProductId())
                .productName("Product Name") // Default value, should be fetched from product service
                .quantity(itemDto.getQuantity())
                .price(itemDto.getPrice())
                .subtotal(itemDto.getSubtotal())
                .build();
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
