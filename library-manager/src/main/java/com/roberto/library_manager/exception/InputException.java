package com.roberto.library_manager.exception;

public class InputException extends RuntimeException {
    public InputException(Object input) {
        super("invalid input: " +input.toString());
    }
}
