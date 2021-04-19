package pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions;

public class ProductCategoryNotFoundException extends Exception{

    public ProductCategoryNotFoundException() {
    }

    public ProductCategoryNotFoundException(String message) {
        super(message);
    }
}
