package pl.com.pt4q.product_manager.modules.environment.services.material_associated.material.exception;

public class EnvMaterialAlreadyExistsException extends Exception {
    public EnvMaterialAlreadyExistsException() {
    }

    public EnvMaterialAlreadyExistsException(String message) {
        super(message);
    }
}
