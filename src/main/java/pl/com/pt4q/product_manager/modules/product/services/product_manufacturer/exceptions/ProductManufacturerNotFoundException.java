package pl.com.pt4q.product_manager.modules.product.services.product_manufacturer.exceptions;

public class ProductManufacturerNotFoundException extends Exception{

    public ProductManufacturerNotFoundException() {
    }

    public ProductManufacturerNotFoundException(String message) {
        super(message);
    }
}
