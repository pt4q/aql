package pl.com.pt4q.product_manager.modules.product.services.unit.exceptions;

public class UnitNotFoundException extends Exception{
    public UnitNotFoundException() {
    }

    public UnitNotFoundException(String message) {
        super(message);
    }
}
