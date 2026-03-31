package com.roberto.library_manager.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super(
                "user not found with id: " + id
        );
    }
    public UserNotFoundException(String username) {
        super(
                "user not found with username: " + username
        );
    }
}
