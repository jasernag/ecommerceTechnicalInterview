package com.ecommerce.inditex.exception;

import com.ecommerce.inditex.exception.handler.PriceException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationResponseEntityExceptionHandlerTest {

    @Test
    public void testHandlePriceException() {
        ApplicationResponseEntityExceptionHandler exceptionHandler = new ApplicationResponseEntityExceptionHandler();
        PriceException exception = new PriceException("Price with id 35455 not found");
        ResponseEntity<Object> response = exceptionHandler.handlePriceException(exception);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode(), "El codigo de estado deberia ser 404.");
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        System.out.println("Response Body: " + responseBody);
        assertEquals(HttpStatus.NOT_FOUND.value(), responseBody.get("status"), "El campo status deberia ser 404.");
        assertEquals("Price Not Found", responseBody.get("error"), "El campo error deberia ser Price Not Found.");
        assertEquals("Price with id 35455 not found", responseBody.get("message"), "El campo message no coincide.");
    }
}
