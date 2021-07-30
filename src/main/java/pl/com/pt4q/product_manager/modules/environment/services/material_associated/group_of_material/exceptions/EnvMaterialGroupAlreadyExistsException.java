package pl.com.pt4q.product_manager.modules.environment.services.material_associated.group_of_material.exceptions;

public class EnvMaterialGroupAlreadyExistsException extends Exception {
    public EnvMaterialGroupAlreadyExistsException() {
    }

    public EnvMaterialGroupAlreadyExistsException(String message) {
        super(message);
    }
}
