package com.roberto.library_manager.exception;

public class BookNotFoundExternallyException extends RuntimeException{
    public BookNotFoundExternallyException(String message){
       super(message);
    }
}
