package com.ecommerce.price.exception.handler;

import java.text.MessageFormat;

public class PriceException extends RuntimeException {
    public PriceException(String message, Object... args) {
        super(MessageFormat.format(message, args));
    }
}
