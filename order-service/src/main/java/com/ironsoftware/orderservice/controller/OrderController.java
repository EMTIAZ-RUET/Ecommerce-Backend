package com.ironsoftware.orderservice.controller;

import com.ironsoftware.orderservice.dto.OrderDto;
import com.ironsoftware.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<OrderDto> getUserOrders(@PathVariable String userId) {
        return orderService.getUserOrders(userId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@RequestBody OrderDto orderDto) {
        return orderService.createOrder(orderDto);
    }
    
    @PutMapping("/{orderId}/confirm")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto confirmOrder(@PathVariable String orderId, @RequestParam String paymentMethodId) {
        return orderService.confirmOrder(orderId, paymentMethodId);
    }
    
    @PutMapping("/{orderId}/cancel")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto cancelOrder(@PathVariable String orderId) {
        return orderService.cancelOrder(orderId);
    }
    
    @GetMapping("/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public OrderDto getOrder(@PathVariable String orderId) {
        return orderService.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found: " + orderId));
    }
}
