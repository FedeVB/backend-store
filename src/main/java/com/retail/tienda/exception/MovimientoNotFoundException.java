package com.retail.tienda.exception;

public class MovimientoNotFoundException extends RuntimeException {

    public MovimientoNotFoundException(String message) {
        super(message);
    }
}