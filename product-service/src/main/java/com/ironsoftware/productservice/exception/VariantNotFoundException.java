package com.ironsoftware.productservice.exception;

public class VariantNotFoundException extends RuntimeException {
    private final String variantId;

    public VariantNotFoundException(String variantId) {
        super("Product variant not found with id: " + variantId);
        this.variantId = variantId;
    }

    public String getVariantId() {
        return variantId;
    }
}