package pl.com.pt4q.product_manager.modules.product.services.product_part_attribute.exceptions;

class ProductPartAttributeNotFoundException extends Exception{
    public ProductPartAttributeNotFoundException() {
    }

    public ProductPartAttributeNotFoundException(String message) {
        super(message);
    }
}
