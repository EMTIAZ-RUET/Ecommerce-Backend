package com.ironsoftware.paymentservice.controller;

import com.ironsoftware.paymentservice.dto.PaymentRequest;
import com.ironsoftware.paymentservice.dto.PaymentResponse;
import com.ironsoftware.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponse processPayment(@Valid @RequestBody PaymentRequest request) {
        return paymentService.processPayment(request);
    }

    @GetMapping("/order/{orderId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponse> getPaymentsByOrder(@PathVariable String orderId) {
        return paymentService.getPaymentsByOrder(orderId);
    }

    @GetMapping("/user/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PaymentResponse> getPaymentsByUser(@PathVariable String userId) {
        return paymentService.getPaymentsByUser(userId);
    }
}
