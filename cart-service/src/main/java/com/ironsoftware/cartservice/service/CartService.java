package com.ironsoftware.cartservice.service;

import com.ironsoftware.cartservice.client.InventoryServiceClient;
import com.ironsoftware.cartservice.client.ProductServiceClient;
import com.ironsoftware.cartservice.event.CartEventPublisher;
import com.ironsoftware.cartservice.exception.CartException;
import com.ironsoftware.cartservice.model.Cart;
import com.ironsoftware.cartservice.model.CartItem;
import com.ironsoftware.cartservice.repository.CartRepository;
import com.ironsoftware.common.dto.ProductDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {
    private final CartRepository cartRepository;
    private final CartEventPublisher eventPublisher;
    private final ProductServiceClient productServiceClient;
    private final InventoryServiceClient inventoryServiceClient;

    @Retry(name = "cartService")
    @CircuitBreaker(name = "cartService")
    public Cart getOrCreateCart(String userId) {
        return cartRepository.findByUserIdAndActive(userId, true)
            .orElseGet(() -> {
                Cart newCart = Cart.builder()
                    .userId(userId)
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build();
                return cartRepository.save(newCart);
            });
    }


    @Transactional
    public Cart addItemToCart(String userId, CartItem item) {
        // Validate product exists
        if (!productServiceClient.productExists(item.getProductId())) {
            throw new CartException("Product not found: " + item.getProductId());
        }
        
        // Get product details for pricing
        ProductDto product = productServiceClient.getProduct(item.getProductId());
        item.setPrice(product.getPrice());
        item.setProductName(product.getName());
        
        // Check inventory availability
        if (!inventoryServiceClient.checkAvailability(item.getProductId(), item.getQuantity())) {
            throw new CartException("Insufficient inventory for product: " + item.getProductId());
        }
        
        Cart cart = getOrCreateCart(userId);
        cart.addItem(item);
        cart.updateTimestamp();
        
        Cart savedCart = cartRepository.save(cart);
        eventPublisher.publishCartUpdatedEvent(savedCart);
        
        return savedCart;
    }

    @Transactional
    public Cart removeItemFromCart(String userId, String productId) {
        Cart cart = getOrCreateCart(userId);
        cart.removeItem(productId);
        cart.updateTimestamp();
        
        Cart savedCart = cartRepository.save(cart);
        eventPublisher.publishCartUpdatedEvent(savedCart);
        
        return savedCart;
    }

    @Transactional
    public Cart updateItemQuantity(String userId, String productId, int quantity) {
        if (quantity <= 0) {
            return removeItemFromCart(userId, productId);
        }
        
        // Check inventory availability for new quantity
        if (!inventoryServiceClient.checkAvailability(productId, quantity)) {
            throw new CartException("Insufficient inventory for product: " + productId);
        }
        
        Cart cart = getOrCreateCart(userId);
        cart.updateItemQuantity(productId, quantity);
        cart.updateTimestamp();
        
        Cart savedCart = cartRepository.save(cart);
        eventPublisher.publishCartUpdatedEvent(savedCart);
        
        return savedCart;
    }

    @Transactional
    public Cart clearCart(String userId) {
        Cart cart = getOrCreateCart(userId);
        cart.clear();
        cart.updateTimestamp();
        
        Cart savedCart = cartRepository.save(cart);
        eventPublisher.publishCartClearedEvent(savedCart);
        
        return savedCart;
    }

    @Transactional
    public void deleteCart(String userId) {
        Cart cart = getOrCreateCart(userId);
        cart.setActive(false);
        cart.updateTimestamp();
        cartRepository.save(cart);
    }
}
