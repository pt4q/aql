package pl.com.pt4q.product_manager.modules.product.services.unit.exceptions;

public class UnitAlreadyExistsException extends Exception {
    public UnitAlreadyExistsException() {
    }

    public UnitAlreadyExistsException(String message) {
        super(message);
    }
}
