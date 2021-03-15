package com.example.application.data.service.product_category.exceptions;

public class ProductCategoryAlreadyExistsException extends Exception{

    public ProductCategoryAlreadyExistsException() {
    }

    public ProductCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
