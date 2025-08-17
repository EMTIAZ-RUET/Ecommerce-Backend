package com.ironsoftware.cartservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@RedisHash("cart")
public class Cart {
    @Id
    private String id;

    @Indexed
    private String userId;

    private List<CartItem> items = new ArrayList<>();
    private BigDecimal subtotal;
    private BigDecimal tax;
    private BigDecimal shippingCost;
    private BigDecimal totalAmount;

    private String couponCode;
    private BigDecimal discountAmount;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime expiresAt;
    private boolean active = true;

    @Version
    private Long version;

    public void addItem(CartItem item) {
        items.add(item);
        recalculateAmounts();
    }

    public void removeItem(String productId) {
        items.removeIf(item -> item.getProductId().equals(productId));
        recalculateAmounts();
    }

    public void updateItemQuantity(String productId, int quantity) {
        items.stream()
            .filter(item -> item.getProductId().equals(productId))
            .findFirst()
            .ifPresent(item -> {
                item.setQuantity(quantity);
                recalculateAmounts();
            });
    }

    private void recalculateAmounts() {
        this.subtotal = items.stream()
            .map(CartItem::getSubtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        // Apply tax and shipping calculations
        this.tax = this.subtotal.multiply(new BigDecimal("0.10")); // 10% tax
        this.totalAmount = this.subtotal.add(this.tax).add(this.shippingCost);

        // Apply discount if coupon exists
        if (this.discountAmount != null) {
            this.totalAmount = this.totalAmount.subtract(this.discountAmount);
        }
    }
    
    public void updateTimestamp() {
        this.updatedAt = LocalDateTime.now();
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public void clear() {
        this.items.clear();
        recalculateAmounts();
    }
    
    // Constructor
    public Cart() {
        this.items = new ArrayList<>();
    }
    
    public Cart(String id, String userId, List<CartItem> items, BigDecimal subtotal, BigDecimal tax, 
               BigDecimal shippingCost, BigDecimal totalAmount, String couponCode, BigDecimal discountAmount,
               LocalDateTime createdAt, LocalDateTime updatedAt, LocalDateTime expiresAt, boolean active, Long version) {
        this.id = id;
        this.userId = userId;
        this.items = items != null ? items : new ArrayList<>();
        this.subtotal = subtotal;
        this.tax = tax;
        this.shippingCost = shippingCost;
        this.totalAmount = totalAmount;
        this.couponCode = couponCode;
        this.discountAmount = discountAmount;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.expiresAt = expiresAt;
        this.active = active;
        this.version = version;
    }
    
    // Builder pattern implementation
    public static CartBuilder builder() {
        return new CartBuilder();
    }
    
    public static class CartBuilder {
        private String id;
        private String userId;
        private List<CartItem> items = new ArrayList<>();
        private BigDecimal subtotal;
        private BigDecimal tax;
        private BigDecimal shippingCost;
        private BigDecimal totalAmount;
        private String couponCode;
        private BigDecimal discountAmount;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        private LocalDateTime expiresAt;
        private boolean active = true;
        private Long version;
        
        public CartBuilder id(String id) {
            this.id = id;
            return this;
        }
        
        public CartBuilder userId(String userId) {
            this.userId = userId;
            return this;
        }
        
        public CartBuilder items(List<CartItem> items) {
            this.items = items;
            return this;
        }
        
        public CartBuilder subtotal(BigDecimal subtotal) {
            this.subtotal = subtotal;
            return this;
        }
        
        public CartBuilder tax(BigDecimal tax) {
            this.tax = tax;
            return this;
        }
        
        public CartBuilder shippingCost(BigDecimal shippingCost) {
            this.shippingCost = shippingCost;
            return this;
        }
        
        public CartBuilder totalAmount(BigDecimal totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }
        
        public CartBuilder couponCode(String couponCode) {
            this.couponCode = couponCode;
            return this;
        }
        
        public CartBuilder discountAmount(BigDecimal discountAmount) {
            this.discountAmount = discountAmount;
            return this;
        }
        
        public CartBuilder createdAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }
        
        public CartBuilder updatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }
        
        public CartBuilder expiresAt(LocalDateTime expiresAt) {
            this.expiresAt = expiresAt;
            return this;
        }
        
        public CartBuilder active(boolean active) {
            this.active = active;
            return this;
        }
        
        public CartBuilder version(Long version) {
            this.version = version;
            return this;
        }
        
        public Cart build() {
            return new Cart(id, userId, items, subtotal, tax, shippingCost, totalAmount, 
                          couponCode, discountAmount, createdAt, updatedAt, expiresAt, active, version);
        }
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public List<CartItem> getItems() {
        return items;
    }
    
    public void setItems(List<CartItem> items) {
        this.items = items;
    }
    
    public BigDecimal getSubtotal() {
        return subtotal;
    }
    
    public void setSubtotal(BigDecimal subtotal) {
        this.subtotal = subtotal;
    }
    
    public BigDecimal getTax() {
        return tax;
    }
    
    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
    
    public BigDecimal getShippingCost() {
        return shippingCost;
    }
    
    public void setShippingCost(BigDecimal shippingCost) {
        this.shippingCost = shippingCost;
    }
    
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    
    public String getCouponCode() {
        return couponCode;
    }
    
    public void setCouponCode(String couponCode) {
        this.couponCode = couponCode;
    }
    
    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }
    
    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public LocalDateTime getExpiresAt() {
        return expiresAt;
    }
    
    public void setExpiresAt(LocalDateTime expiresAt) {
        this.expiresAt = expiresAt;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public Long getVersion() {
        return version;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
}
