package com.roberto.library_manager.exception;

public class BookNotFoundException extends RuntimeException{
    public BookNotFoundException(Long id){
        super("book not found with id: " + id);
    }
    public BookNotFoundException(String message){
        super(message);
    }
}
