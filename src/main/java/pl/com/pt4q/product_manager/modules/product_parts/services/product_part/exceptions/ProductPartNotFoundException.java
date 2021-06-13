package pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions;

public class ProductPartNotFoundException extends Exception{
    public ProductPartNotFoundException() {
    }

    public ProductPartNotFoundException(String message) {
        super(message);
    }
}
