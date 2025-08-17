package com.ironsoftware.cartservice.event;

public class CartEvent {
    private String cartId;
    private String userId;
    private CartEventType eventType;
    private String productId;
    private Integer quantity;
    private String timestamp;
    
    public CartEvent() {
    }
    
    public CartEvent(String cartId, String userId, CartEventType eventType, String productId, Integer quantity, String timestamp) {
        this.cartId = cartId;
        this.userId = userId;
        this.eventType = eventType;
        this.productId = productId;
        this.quantity = quantity;
        this.timestamp = timestamp;
    }
    
    public String getCartId() {
        return cartId;
    }
    
    public void setCartId(String cartId) {
        this.cartId = cartId;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public CartEventType getEventType() {
        return eventType;
    }
    
    public void setEventType(CartEventType eventType) {
        this.eventType = eventType;
    }
    
    public String getProductId() {
        return productId;
    }
    
    public void setProductId(String productId) {
        this.productId = productId;
    }
    
    public Integer getQuantity() {
        return quantity;
    }
    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public enum CartEventType {
        CART_CREATED,
        ITEM_ADDED,
        ITEM_REMOVED,
        QUANTITY_UPDATED,
        CART_CLEARED,
        CART_DELETED
    }
}
