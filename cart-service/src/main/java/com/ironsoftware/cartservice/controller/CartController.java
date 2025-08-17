package com.ironsoftware.cartservice.controller;

import com.ironsoftware.cartservice.model.Cart;
import com.ironsoftware.cartservice.model.CartItem;
import com.ironsoftware.cartservice.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/carts")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/{userId}")
    public Mono<ResponseEntity<Cart>> getCart(@PathVariable String userId) {
        return cartService.getOrCreateCart(userId)
            .map(ResponseEntity::ok);
    }

    @PostMapping("/{userId}/items")
    public Mono<ResponseEntity<Cart>> addItemToCart(
            @PathVariable String userId,
            @RequestBody CartItem item) {
        return cartService.addItemToCart(userId, item)
            .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{userId}/items/{productId}")
    public Mono<ResponseEntity<Cart>> removeItemFromCart(
            @PathVariable String userId,
            @PathVariable String productId) {
        return cartService.removeItemFromCart(userId, productId)
            .map(ResponseEntity::ok);
    }

    @PutMapping("/{userId}/items/{productId}")
    public Mono<ResponseEntity<Cart>> updateItemQuantity(
            @PathVariable String userId,
            @PathVariable String productId,
            @RequestParam int quantity) {
        return cartService.updateItemQuantity(userId, productId, quantity)
            .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{userId}/items")
    public Mono<ResponseEntity<Cart>> clearCart(@PathVariable String userId) {
        return cartService.clearCart(userId)
            .map(ResponseEntity::ok);
    }

    @DeleteMapping("/{userId}")
    public Mono<ResponseEntity<Void>> deleteCart(@PathVariable String userId) {
        return cartService.deleteCart(userId)
            .then(Mono.just(ResponseEntity.noContent().<Void>build()));
    }
}
