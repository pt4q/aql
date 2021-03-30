package com.example.application.data.service.product_category.exceptions;

public class ProductCategoryNotFoundException extends Exception{

    public ProductCategoryNotFoundException() {
    }

    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
}
