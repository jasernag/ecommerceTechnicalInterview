package com.ecommerce.price.exception;


import com.ecommerce.price.exception.handler.PriceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ApplicationResponseEntityExceptionHandler {

    @ExceptionHandler(PriceException.class)
    public ResponseEntity<Object> handlePriceException(PriceException exception) {
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpStatus.NOT_FOUND.value());
        errorResponse.put("error", "Price Not Found");
        errorResponse.put("message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }
}
