package pl.com.pt4q.product_manager.modules.product.services.product.exceptions;

public class ProductAlreadyExistsException extends Exception{
    public ProductAlreadyExistsException() {
    }

    public ProductAlreadyExistsException(String message) {
        super(message);
    }
}
