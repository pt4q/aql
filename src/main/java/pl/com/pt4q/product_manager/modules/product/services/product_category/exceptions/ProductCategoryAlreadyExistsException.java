package pl.com.pt4q.product_manager.modules.product.services.product_category.exceptions;

public class ProductCategoryAlreadyExistsException extends Exception{

    public ProductCategoryAlreadyExistsException() {
    }

    public ProductCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
