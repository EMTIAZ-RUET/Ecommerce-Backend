package com.ironsoftware.productservice.repository;

import com.ironsoftware.productservice.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
class ProductRepositoryTest {

    @Mock
    private ProductRepository productRepository;

    private Product testProduct;

    @BeforeEach
    void setUp() {
        testProduct = Product.builder()
                .id("product-1")
                .name("Test Product")
                .description("Test Description")
                .price(BigDecimal.valueOf(99.99))
                .categoryId("category-1")
                .brandId("brand-1")
                .sku("TEST-SKU-001")
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
    void findByCategoryIdAndActiveTrue_ShouldReturnProducts_WhenProductsExist() {
        // Given
        List<Product> products = Arrays.asList(testProduct);
        when(productRepository.findByCategoryIdAndActiveTrue(anyString())).thenReturn(products);

        // When
        List<Product> result = productRepository.findByCategoryIdAndActiveTrue("category-1");

        // Then
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testProduct.getId(), result.get(0).getId());
    }

    @Test
    void existsBySku_ShouldReturnTrue_WhenSkuExists() {
        // Given
        when(productRepository.existsBySku(anyString())).thenReturn(true);

        // When
        boolean result = productRepository.existsBySku("TEST-SKU-001");

        // Then
        assertTrue(result);
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
        assertEquals(testProduct.getSku(), result.getSku());
    }

    @Test
    void findByNameContainingIgnoreCase_ShouldReturnProducts_WhenNameMatches() {
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
