package com.efutures.test.product_management.exception;

public class ProductDeleteException extends RuntimeException{
    public ProductDeleteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductDeleteException(String message) {
        super(message);
    }
}
