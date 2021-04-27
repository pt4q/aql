package pl.com.pt4q.product_manager.modules.test_card.services.parameter_category.exceptions;

public class ParameterCategoryAlreadyExistsException extends Exception{
    public ParameterCategoryAlreadyExistsException() {
    }

    public ParameterCategoryAlreadyExistsException(String message) {
        super(message);
    }
}
