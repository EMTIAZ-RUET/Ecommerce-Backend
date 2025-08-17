package com.ironsoftware.orderservice.client;

import com.ironsoftware.orderservice.dto.PaymentRequest;
import com.ironsoftware.orderservice.dto.PaymentResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "payment-service", url = "${services.payment-service.url:http://localhost:8085}")
public interface PaymentServiceClient {
    
    @PostMapping("/api/payments")
    PaymentResponse processPayment(@RequestBody PaymentRequest request);
    
    @GetMapping("/api/payments/order/{orderId}")
    List<PaymentResponse> getPaymentsByOrder(@PathVariable String orderId);
}
