package com.example.application.modules.test_card.services.test_card.exceptions;

public class TestCardAlreadyExistsException extends Exception{

    public TestCardAlreadyExistsException() {
    }

    public TestCardAlreadyExistsException(String message) {
        super(message);
    }
}
