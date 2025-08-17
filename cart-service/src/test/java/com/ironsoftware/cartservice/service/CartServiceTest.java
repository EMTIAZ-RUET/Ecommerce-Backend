package com.ironsoftware.cartservice.service;

import com.ironsoftware.cartservice.model.Cart;
import com.ironsoftware.cartservice.model.CartItem;
import com.ironsoftware.cartservice.repository.CartRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private CartRepository cartRepository;

    @InjectMocks
    private CartService cartService;

    private Cart testCart;
    private CartItem testCartItem;

    @BeforeEach
    void setUp() {
        testCartItem = CartItem.builder()
                .productId("product-1")
                .productName("Test Product")
                .quantity(2)
                .price(BigDecimal.valueOf(50.00))
                .subtotal(BigDecimal.valueOf(100.00))
                .build();

        List<CartItem> items = new ArrayList<>();
        items.add(testCartItem);

        testCart = Cart.builder()
                .id("cart-1")
                .userId("user-1")
                .items(items)
                .totalAmount(BigDecimal.valueOf(100.00))
                .totalItems(2)
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .version(1L)
                .build();
    }

    @Test
    void getCartByUserId_ShouldReturnCart_WhenCartExists() {
        // Given
        when(cartRepository.findByUserId(anyString())).thenReturn(Optional.of(testCart));

        // When
        Optional<Cart> result = cartRepository.findByUserId("user-1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testCart.getUserId(), result.get().getUserId());
        assertEquals(testCart.getTotalAmount(), result.get().getTotalAmount());
    }

    @Test
    void getCartByUserId_ShouldReturnEmpty_WhenCartNotExists() {
        // Given
        when(cartRepository.findByUserId(anyString())).thenReturn(Optional.empty());

        // When
        Optional<Cart> result = cartRepository.findByUserId("nonexistent-user");

        // Then
        assertFalse(result.isPresent());
    }

    @Test
    void saveCart_ShouldReturnSavedCart_WhenValidCart() {
        // Given
        when(cartRepository.save(any(Cart.class))).thenReturn(testCart);

        // When
        Cart result = cartRepository.save(testCart);

        // Then
        assertNotNull(result);
        assertEquals(testCart.getId(), result.getId());
        assertEquals(testCart.getUserId(), result.getUserId());
        assertEquals(testCart.getTotalAmount(), result.getTotalAmount());
    }

    @Test
    void deleteCart_ShouldCallRepositoryDelete_WhenValidId() {
        // Given
        String cartId = "cart-1";
        doNothing().when(cartRepository).deleteById(cartId);

        // When
        cartRepository.deleteById(cartId);

        // Then
        verify(cartRepository, times(1)).deleteById(cartId);
    }

    @Test
    void cartItem_ShouldCalculateSubtotal_WhenQuantityAndPriceSet() {
        // Given
        CartItem item = CartItem.builder()
                .productId("product-2")
                .quantity(3)
                .price(BigDecimal.valueOf(25.00))
                .build();

        // When
        BigDecimal expectedSubtotal = item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()));

        // Then
        assertEquals(BigDecimal.valueOf(75.00), expectedSubtotal);
    }

    @Test
    void cart_ShouldCalculateTotalAmount_WhenItemsAdded() {
        // Given
        CartItem item1 = CartItem.builder()
                .productId("product-1")
                .quantity(2)
                .price(BigDecimal.valueOf(50.00))
                .subtotal(BigDecimal.valueOf(100.00))
                .build();

        CartItem item2 = CartItem.builder()
                .productId("product-2")
                .quantity(1)
                .price(BigDecimal.valueOf(30.00))
                .subtotal(BigDecimal.valueOf(30.00))
                .build();

        List<CartItem> items = List.of(item1, item2);

        // When
        BigDecimal totalAmount = items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Then
        assertEquals(BigDecimal.valueOf(130.00), totalAmount);
    }
}
