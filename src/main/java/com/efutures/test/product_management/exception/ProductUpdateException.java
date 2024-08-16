package com.efutures.test.product_management.exception;

public class ProductUpdateException extends RuntimeException{
    public ProductUpdateException(String message, Throwable cause) {
        super(message, cause);
    }
}
