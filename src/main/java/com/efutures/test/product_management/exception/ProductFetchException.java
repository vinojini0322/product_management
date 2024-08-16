package com.efutures.test.product_management.exception;

public class ProductFetchException extends RuntimeException{
    public ProductFetchException(String message, Throwable cause) {
        super(message, cause);
    }

    public ProductFetchException() {
    }
}
