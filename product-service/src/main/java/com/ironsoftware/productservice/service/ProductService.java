package com.ironsoftware.productservice.service;

import com.ironsoftware.common.dto.ProductDto;
import com.ironsoftware.productservice.event.ProductEventPublisher;
import com.ironsoftware.productservice.exception.InsufficientStockException;
import com.ironsoftware.productservice.exception.ProductNotFoundException;
import com.ironsoftware.productservice.exception.VariantNotFoundException;
import com.ironsoftware.productservice.model.Product;
import com.ironsoftware.productservice.model.ProductVariant;
import com.ironsoftware.productservice.repository.ProductRepository;
import com.ironsoftware.productservice.repository.ProductVariantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {
    
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(ProductService.class);
    private final ProductRepository productRepository;
    private final ProductVariantRepository productVariantRepository;
    private final ProductEventPublisher eventPublisher;

    public List<ProductDto> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    public ProductDto getProduct(String id) {
        return productRepository.findById(id)
                .map(this::mapToDto)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        product.setCreatedAt(Instant.now());
        product.setUpdatedAt(Instant.now());
        product.setActive(true);
        product = productRepository.save(product);
        
        // Publish product created event
        eventPublisher.publishProductCreatedEvent(product);
        log.info("Product created with ID: {}", product.getId());
        
        return mapToDto(product);
    }
    
    public ProductDto updateProduct(String id, ProductDto productDto) {
        return productRepository.findById(id)
                .map(existingProduct -> {
                    // Update product fields
                    existingProduct.setName(productDto.getName());
                    existingProduct.setDescription(productDto.getDescription());
                    existingProduct.setPrice(productDto.getPrice());
                    existingProduct.setStock(productDto.getQuantity());
                    existingProduct.setCategory(productDto.getCategory());
                    existingProduct.setUpdatedAt(Instant.now());
                    
                    Product updatedProduct = productRepository.save(existingProduct);
                    
                    // Publish product updated event
                    eventPublisher.publishProductUpdatedEvent(updatedProduct);
                    log.info("Product updated with ID: {}", updatedProduct.getId());
                    
                    return mapToDto(updatedProduct);
                })
                .orElseThrow(() -> new ProductNotFoundException(id));
    }
    
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
        
        productRepository.deleteById(id);
        
        // Publish product deleted event
        eventPublisher.publishProductDeletedEvent(product);
        log.info("Product deleted with ID: {}", id);
    }

    private ProductDto mapToDto(Product product) {
        return ProductDto.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .category(product.getCategory())
                .quantity(product.getStock())
                .build();
    }

    private Product mapToEntity(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .description(productDto.getDescription())
                .price(productDto.getPrice())
                .stock(productDto.getQuantity())
                .category(productDto.getCategory())
                .active(true)
                .build();
    }
    
    /**
     * Stock management methods
     */
    @Transactional
    public void updateStock(String productId, Integer newStock) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        
        Integer previousStock = product.getStock();
        product.updateStock(newStock);
        productRepository.save(product);
        
        // Publish stock updated event
        eventPublisher.publishStockUpdatedEvent(product, previousStock);
        log.info("Stock updated for product ID: {} from {} to {}", productId, previousStock, newStock);
    }
    
    @Transactional
    public boolean checkStock(String productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        
        return product.getStock() != null && product.getStock() >= quantity;
    }
    
    @Transactional
    public void reserveStock(String productId, Integer quantity) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductNotFoundException(productId));
        
        if (product.getStock() == null || product.getStock() < quantity) {
            throw new InsufficientStockException(productId, null, quantity,
                    product.getStock() != null ? product.getStock() : 0);
        }
        
        Integer previousStock = product.getStock();
        product.updateStock(product.getStock() - quantity);
        productRepository.save(product);
        
        // Publish stock updated event
        eventPublisher.publishStockUpdatedEvent(product, previousStock);
        log.info("Reserved {} units for product ID: {}, remaining stock: {}",
                quantity, productId, product.getStock());
    }
    
    // Product variant methods
    public List<ProductVariant> getProductVariants(String productId) {
        return productVariantRepository.findByProductId(productId);
    }
    
    public ProductVariant createProductVariant(ProductVariant variant) {
        variant.setCreatedAt(Instant.now());
        variant.setUpdatedAt(Instant.now());
        return productVariantRepository.save(variant);
    }
    
    @Transactional
    public void updateVariantStock(String variantId, Integer newStock) {
        ProductVariant variant = productVariantRepository.findById(variantId)
                .orElseThrow(() -> new VariantNotFoundException(variantId));
        
        variant.updateStock(newStock);
        productVariantRepository.save(variant);
        
        log.info("Stock updated for variant ID: {} to {}", variantId, newStock);
    }

    @Transactional(readOnly = true)
    public boolean productExists(String id) {
        return productRepository.existsById(id);
    }
}
