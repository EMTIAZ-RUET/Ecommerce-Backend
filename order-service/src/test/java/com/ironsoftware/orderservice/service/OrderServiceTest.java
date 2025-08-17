package com.ironsoftware.orderservice.service;

import com.ironsoftware.orderservice.dto.OrderDto;
import com.ironsoftware.orderservice.dto.OrderItemDto;
import com.ironsoftware.orderservice.entity.Order;
import com.ironsoftware.orderservice.entity.OrderItem;
import com.ironsoftware.orderservice.entity.OrderStatus;
import com.ironsoftware.orderservice.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;

    @InjectMocks
    private OrderService orderService;

    private Order testOrder;
    private OrderDto orderDto;
    private OrderItemDto orderItemDto;

    @BeforeEach
    void setUp() {
        OrderItem orderItem = OrderItem.builder()
                .id(UUID.randomUUID().toString())
                .productId("product-1")
                .quantity(2)
                .price(BigDecimal.valueOf(50.00))
                .subtotal(BigDecimal.valueOf(100.00))
                .build();

        testOrder = Order.builder()
                .id(UUID.randomUUID().toString())
                .userId("user-1")
                .status(OrderStatus.CREATED)
                .totalAmount(BigDecimal.valueOf(100.00))
                .items(Arrays.asList(orderItem))
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        orderItemDto = OrderItemDto.builder()
                .productId("product-1")
                .quantity(2)
                .price(BigDecimal.valueOf(50.00))
                .subtotal(BigDecimal.valueOf(100.00))
                .build();

        orderDto = OrderDto.builder()
                .userId("user-1")
                .items(Arrays.asList(orderItemDto))
                .totalAmount(BigDecimal.valueOf(100.00))
                .build();
    }

    @Test
    void createOrder_ShouldReturnOrderDto_WhenValidRequest() {
        // Given
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        // When
        OrderDto result = orderService.createOrder(orderDto);

        // Then
        assertNotNull(result);
        assertEquals(testOrder.getUserId(), result.getUserId());
        assertEquals(testOrder.getTotalAmount(), result.getTotalAmount());
        assertEquals(OrderStatus.CREATED, result.getStatus());
        verify(orderRepository).save(any(Order.class));
        verify(kafkaTemplate).send(anyString(), any());
    }

    @Test
    void getOrderById_ShouldReturnOrderDto_WhenOrderExists() {
        // Given
        String orderId = testOrder.getId();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrder));

        // When
        OrderDto result = orderService.getOrderById(orderId);

        // Then
        assertNotNull(result);
        assertEquals(testOrder.getId(), result.getId());
        assertEquals(testOrder.getUserId(), result.getUserId());
        assertEquals(testOrder.getStatus(), result.getStatus());
    }

    @Test
    void getOrderById_ShouldThrowException_WhenOrderNotFound() {
        // Given
        String orderId = "nonexistent";
        when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(RuntimeException.class, () -> orderService.getOrderById(orderId));
    }

    @Test
    void getOrdersByUserId_ShouldReturnOrderList_WhenOrdersExist() {
        // Given
        String userId = "user-1";
        List<Order> orders = Arrays.asList(testOrder);
        when(orderRepository.findByUserId(userId)).thenReturn(orders);

        // When
        List<OrderDto> result = orderService.getOrdersByUserId(userId);

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testOrder.getId(), result.get(0).getId());
    }

    @Test
    void updateOrderStatus_ShouldUpdateStatus_WhenOrderExists() {
        // Given
        String orderId = testOrder.getId();
        OrderStatus newStatus = OrderStatus.PAID;
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrder));
        when(orderRepository.save(any(Order.class))).thenReturn(testOrder);

        // When
        orderService.updateOrderStatus(orderId, newStatus);

        // Then
        verify(orderRepository).save(argThat(order -> order.getStatus() == newStatus));
    }

    @Test
    void cancelOrder_ShouldSetStatusToCancelled_WhenOrderExists() {
        // Given
        String orderId = testOrder.getId();
        when(orderRepository.findById(orderId)).thenReturn(Optional.of(testOrder));

        // When
        orderService.cancelOrder(orderId);

        // Then
        verify(orderRepository).save(argThat(order -> order.getStatus() == OrderStatus.CANCELLED));
    }
}
