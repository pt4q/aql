package pl.com.pt4q.product_manager.modules.test_card.services.test_card.exceptions;

public class TestCardAlreadyExistsException extends Exception{

    public TestCardAlreadyExistsException() {
    }

    public TestCardAlreadyExistsException(String message) {
        super(message);
    }
}
