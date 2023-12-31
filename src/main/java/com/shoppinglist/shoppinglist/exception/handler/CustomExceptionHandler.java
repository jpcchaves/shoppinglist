package com.shoppinglist.shoppinglist.exception.handler;

import com.shoppinglist.shoppinglist.exception.ResourceNotFoundException;
import com.shoppinglist.shoppinglist.exception.model.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.Objects;
import java.util.logging.Logger;

@ControllerAdvice
@RestController
public class CustomExceptionHandler {
    static final Logger logger = Logger.getLogger("Exception");

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleUnexpectedExceptions(
            Exception ex,
            WebRequest request) {
        logger.severe("Error: " + ex.getClass() + " Message: " + ex.getMessage());
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundException(
            ResourceNotFoundException ex,
            WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), Objects.requireNonNull(
                ex.getFieldError()).getDefaultMessage(),
                request.getDescription(false));

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


}
