package pl.com.pt4q.product_manager.modules.product.services.product.exceptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException() {
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
