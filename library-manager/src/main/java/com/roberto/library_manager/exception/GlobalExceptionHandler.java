package com.roberto.library_manager.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        printRow(ex);
        return ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFound(UserNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        printRow(ex);
        return ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(BookNotFoundExternallyException.class)
    public ResponseEntity<ErrorResponse> handleBookNotFoundExternally(BookNotFoundExternallyException ex) {
        ErrorResponse error = new ErrorResponse(404, ex.getMessage());
        printRow(ex);
        return ResponseEntity.status(404).body(error);
    }
    @ExceptionHandler(InputException.class)
    public ResponseEntity<ErrorResponse> handleInputException(InputException ex) {
        log.error("Input exception: {}", ex.getMessage());
        StackTraceElement origin = ex.getStackTrace()[0];
        printRow(ex);
        return ResponseEntity.status(400).body(new ErrorResponse(400, ex.getMessage()));
    }
    @ExceptionHandler(HandlerMethodValidationException.class)
    public ResponseEntity<ErrorResponse> handleHandlerMethodValidation(HandlerMethodValidationException ex) {
        String message = ex.getAllErrors().stream()
                .map(MessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));
        log.error("Validation exception: {}", message);
        StackTraceElement origin = ex.getStackTrace()[0];
        printRow(ex);
        return ResponseEntity.status(400).body(new ErrorResponse(400, message));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .collect(Collectors.joining(", "));
        printRow(ex);
        return ResponseEntity.status(400).body(new ErrorResponse(400, message));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex) {
        log.error("Unhandled exception on {}: {}", ex.getClass().getName(), ex.getMessage());
        ErrorResponse error = new ErrorResponse(500, "Internal server error");
        printRow(ex);
        return ResponseEntity.status(500).body(error);
    }

    private void printRow(Exception ex){
        StackTraceElement origin = ex.getStackTrace()[0];
        log.error("Exception thrown at: {}.{}() line {}",
                origin.getClassName(),
                origin.getMethodName(),
                origin.getLineNumber());
    }

}