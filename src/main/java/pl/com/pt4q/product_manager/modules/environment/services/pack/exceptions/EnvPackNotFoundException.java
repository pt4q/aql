package pl.com.pt4q.product_manager.modules.environment.services.pack.exceptions;

public class EnvPackNotFoundException extends Exception{
    public EnvPackNotFoundException() {
    }

    public EnvPackNotFoundException(String message) {
        super(message);
    }
}
