package com.ironsoftware.cartservice.service;

import com.ironsoftware.cartservice.model.Cart;
import com.ironsoftware.cartservice.model.CartItem;
import com.ironsoftware.cartservice.repository.CartRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class CartService {
    private static final Logger log = LoggerFactory.getLogger(CartService.class);
    private final CartRepository cartRepository;
    
    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Retry(name = "cartService", fallbackMethod = "getCartFallback")
    @CircuitBreaker(name = "cartService", fallbackMethod = "getCartFallback")
    public Mono<Cart> getOrCreateCart(String userId) {
        return Mono.fromSupplier(() -> 
            cartRepository.findByUserIdAndActive(userId, true)
                .orElseGet(() -> {
                    Cart newCart = Cart.builder()
                        .userId(userId)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();
                    return cartRepository.save(newCart);
                })
        );
    }

    private Mono<Cart> getCartFallback(String userId, Exception ex) {
        log.error("Fallback: Failed to get cart for user {}", userId, ex);
        return Mono.just(Cart.builder()
                .userId(userId)
                .items(new ArrayList<>())
                .build());
    }

    public Mono<Cart> addItemToCart(String userId, CartItem item) {
        return Mono.fromSupplier(() -> {
            Cart cart = getOrCreateCart(userId).block();
            cart.addItem(item);
            cart.updateTimestamp();
            return cartRepository.save(cart);
        });
    }

    public Mono<Cart> removeItemFromCart(String userId, String productId) {
        return Mono.fromSupplier(() -> {
            Cart cart = getOrCreateCart(userId).block();
            cart.removeItem(productId);
            cart.updateTimestamp();
            return cartRepository.save(cart);
        });
    }

    public Mono<Cart> updateItemQuantity(String userId, String productId, int quantity) {
        return Mono.fromSupplier(() -> {
            Cart cart = getOrCreateCart(userId).block();
            cart.updateItemQuantity(productId, quantity);
            cart.updateTimestamp();
            return cartRepository.save(cart);
        });
    }

    public Mono<Cart> clearCart(String userId) {
        return Mono.fromSupplier(() -> {
            Cart cart = getOrCreateCart(userId).block();
            cart.clear();
            cart.updateTimestamp();
            return cartRepository.save(cart);
        });
    }

    public Mono<Void> deleteCart(String userId) {
        return Mono.fromRunnable(() -> {
            Cart cart = getOrCreateCart(userId).block();
            cart.setActive(false);
            cart.updateTimestamp();
            cartRepository.save(cart);
        });
    }
}
