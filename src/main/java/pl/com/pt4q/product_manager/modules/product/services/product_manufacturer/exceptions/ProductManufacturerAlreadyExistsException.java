package pl.com.pt4q.product_manager.modules.product.services.product_manufacturer.exceptions;

public class ProductManufacturerAlreadyExistsException extends Exception{

    public ProductManufacturerAlreadyExistsException() {
    }

    public ProductManufacturerAlreadyExistsException(String message) {
        super(message);
    }
}
