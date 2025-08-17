package com.ironsoftware.productservice.exception;

public class InsufficientStockException extends RuntimeException {
    private final String productId;
    private final String variantId;
    private final int requestedQuantity;
    private final int availableQuantity;

    public InsufficientStockException(String productId, String variantId, int requestedQuantity, int availableQuantity) {
        super(String.format("Insufficient stock for product %s%s. Requested: %d, Available: %d", 
                productId, 
                variantId != null ? " variant " + variantId : "", 
                requestedQuantity, 
                availableQuantity));
        this.productId = productId;
        this.variantId = variantId;
        this.requestedQuantity = requestedQuantity;
        this.availableQuantity = availableQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getVariantId() {
        return variantId;
    }

    public int getRequestedQuantity() {
        return requestedQuantity;
    }

    public int getAvailableQuantity() {
        return availableQuantity;
    }
}