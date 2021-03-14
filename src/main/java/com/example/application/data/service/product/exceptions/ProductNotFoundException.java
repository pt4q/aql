package com.example.application.data.service.product.exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
