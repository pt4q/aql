package com.example.application.modules.product.services.product_category.exceptions;

public class ProductCategoryNotFoundException extends Exception{

    public ProductCategoryNotFoundException() {
    }

    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
}
