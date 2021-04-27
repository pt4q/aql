package pl.com.pt4q.product_manager.modules.product.services.manufacturer.exceptions;

public class ManufacturerNotFoundException extends Exception{

    public ManufacturerNotFoundException() {
    }

    public ManufacturerNotFoundException(String message) {
        super(message);
    }
}
