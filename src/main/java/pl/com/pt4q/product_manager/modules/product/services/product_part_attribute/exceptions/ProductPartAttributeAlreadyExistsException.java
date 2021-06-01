package pl.com.pt4q.product_manager.modules.product.services.product_part_attribute.exceptions;

public class ProductPartAttributeAlreadyExistsException extends Exception{
    public ProductPartAttributeAlreadyExistsException() {
    }

    public ProductPartAttributeAlreadyExistsException(String message) {
        super(message);
    }
}
