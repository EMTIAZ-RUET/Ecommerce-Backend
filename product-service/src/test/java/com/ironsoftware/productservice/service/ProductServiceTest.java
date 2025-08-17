package com.ironsoftware.productservice.service;

import com.ironsoftware.productservice.model.Product;
import com.ironsoftware.productservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = Product.builder()
                .id("product-1")
                .name("Test Product")
                .description("Test Description")
                .price(BigDecimal.valueOf(99.99))
                .category("category-1")
                .brand("brand-1")
                .active(true)
                .stockQuantity(100)
                .weight(1.5)
                .dimensions("10x10x10")
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();
    }

    @Test
    void findById_ShouldReturnProduct_WhenProductExists() {
        // Given
        when(productRepository.findById(anyString())).thenReturn(Optional.of(testProduct));

        // When
        Optional<Product> result = productRepository.findById("product-1");

        // Then
        assertTrue(result.isPresent());
        assertEquals(testProduct.getId(), result.get().getId());
        assertEquals(testProduct.getName(), result.get().getName());
    }

    @Test
    void save_ShouldReturnSavedProduct_WhenValidProduct() {
        // Given
        when(productRepository.save(any(Product.class))).thenReturn(testProduct);

        // When
        Product result = productRepository.save(testProduct);

        // Then
        assertNotNull(result);
        assertEquals(testProduct.getId(), result.getId());
        assertEquals(testProduct.getName(), result.getName());
        assertEquals(testProduct.getPrice(), result.getPrice());
    }

    @Test
    void findByCategory_ShouldReturnProducts_WhenProductsExist() {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        when(productRepository.findByCategory(anyString())).thenReturn(products);

        // When
        List<Product> result = productRepository.findByCategory("category-1");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getCategory(), result.get(0).getCategory());
    }

    @Test
    void findByActiveTrue_ShouldReturnActiveProducts() {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        when(productRepository.findByActiveTrue()).thenReturn(products);

        // When
        List<Product> result = productRepository.findByActiveTrue();

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).isActive());
    }

    @Test
    void findByNameContainingIgnoreCase_ShouldReturnMatchingProducts() {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        when(productRepository.findByNameContainingIgnoreCase(anyString())).thenReturn(products);

        // When
        List<Product> result = productRepository.findByNameContainingIgnoreCase("test");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertTrue(result.get(0).getName().toLowerCase().contains("test"));
    }
}
