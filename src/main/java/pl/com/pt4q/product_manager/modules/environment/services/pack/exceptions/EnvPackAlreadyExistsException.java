package pl.com.pt4q.product_manager.modules.environment.services.pack.exceptions;

public class EnvPackAlreadyExistsException extends Exception {
    public EnvPackAlreadyExistsException() {
    }

    public EnvPackAlreadyExistsException(String message) {
        super(message);
    }
}
