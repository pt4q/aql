package com.example.application.data.service.product;

public class ProductNotFound extends Exception{

    public ProductNotFound() {
    }

    public ProductNotFound(String message) {
        super(message);
    }
}
