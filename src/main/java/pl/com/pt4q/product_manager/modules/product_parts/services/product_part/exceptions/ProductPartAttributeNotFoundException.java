package pl.com.pt4q.product_manager.modules.product_parts.services.product_part.exceptions;

public class ProductPartAttributeNotFoundException extends Exception{
    public ProductPartAttributeNotFoundException() {
    }

    public ProductPartAttributeNotFoundException(String message) {
        super(message);
    }
}
