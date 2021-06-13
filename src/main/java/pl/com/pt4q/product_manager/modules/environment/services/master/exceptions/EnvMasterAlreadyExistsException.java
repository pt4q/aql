package pl.com.pt4q.product_manager.modules.environment.services.master.exceptions;

public class EnvMasterAlreadyExistsException extends Exception{
    public EnvMasterAlreadyExistsException() {
    }

    public EnvMasterAlreadyExistsException(String message) {
        super(message);
    }
}
