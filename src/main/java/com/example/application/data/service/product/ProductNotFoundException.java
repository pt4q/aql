package com.example.application.data.service.product;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
