package pl.com.pt4q.product_manager.modules.environment.services.weee.exceptions;

public class EnvWeeeAlreadyExistsException extends Exception {
    public EnvWeeeAlreadyExistsException() {
    }

    public EnvWeeeAlreadyExistsException(String message) {
        super(message);
    }
}
