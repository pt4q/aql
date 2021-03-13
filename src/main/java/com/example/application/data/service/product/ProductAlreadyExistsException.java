package com.example.application.data.service.product;

public class ProductAlreadyExistsException extends Exception{

    public ProductAlreadyExistsException() {
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
