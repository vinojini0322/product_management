package com.efutures.test.product_management.exception;

import com.efutures.test.product_management.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ApiResponse<?>> handleGlobalException(Exception exception) {
        ApiResponse<?> response = ApiResponse.error(exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ProductCreationException.class)
    public ResponseEntity<ApiResponse<?>> handleProductCreationException(ProductCreationException productCreationException){
        ApiResponse<?> response = ApiResponse.error(productCreationException.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
    @ExceptionHandler({ProductUpdateException.class,ProductFetchException.class,ProductDeleteException.class})
    public ResponseEntity<ApiResponse<?>> handleProductUpdateException(ProductUpdateException productUpdateException){
        ApiResponse<?> response = ApiResponse.error(productUpdateException.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ApiResponse<?>> handleResponseStatusException(ResponseStatusException responseStatusException) {
        ApiResponse<String> response = ApiResponse.error(responseStatusException.getReason());
        return new ResponseEntity<>(response, responseStatusException.getStatusCode());
    }

}
