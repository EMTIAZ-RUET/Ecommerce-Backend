package com.ironsoftware.cartservice.validation;

import com.ironsoftware.cartservice.exception.CartException;
import com.ironsoftware.cartservice.model.CartItem;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class CartValidator {

    public void validateCartItem(CartItem item) {
        if (item == null) {
            throw new CartException("Cart item cannot be null", HttpStatus.BAD_REQUEST);
        }

        if (item.getProductId() == null || item.getProductId().trim().isEmpty()) {
            throw new CartException("Product ID is required", HttpStatus.BAD_REQUEST);
        }

        if (item.getQuantity() == null || item.getQuantity() <= 0) {
            throw new CartException("Quantity must be greater than 0", HttpStatus.BAD_REQUEST);
        }

        if (item.getPrice() == null || item.getPrice().compareTo(BigDecimal.ZERO) < 0) {
            throw new CartException("Price must be non-negative", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateQuantity(int quantity) {
        if (quantity <= 0) {
            throw new CartException("Quantity must be greater than 0", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateUserId(String userId) {
        if (userId == null || userId.trim().isEmpty()) {
            throw new CartException("User ID is required", HttpStatus.BAD_REQUEST);
        }
    }
}
