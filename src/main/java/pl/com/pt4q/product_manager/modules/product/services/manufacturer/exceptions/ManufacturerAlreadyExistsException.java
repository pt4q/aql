package pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions;

public class ManufacturerAlreadyExistsException extends Exception{

    public ManufacturerAlreadyExistsException() {
    }

    public ManufacturerAlreadyExistsException(String message) {
        super(message);
    }
}
