package pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions;

public class ProductPartAlreadyExistsException extends Exception {
    public ProductPartAlreadyExistsException() {
    }

    public ProductPartAlreadyExistsException(String message) {
        super(message);
    }
}
