package com.spandigital.ddd.freelancer.controller.exception;

import com.spandigital.ddd.freelancer.application.exception.ApplicationException;
import com.spandigital.ddd.freelancer.domain.exception.DomainException;
import com.spandigital.ddd.freelancer.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {

        Map<String, String> errors = new HashMap<>();

        exception
                .getBindingResult()
                .getAllErrors()
                .forEach((error) -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });

        return errors;
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ApplicationException.class})
    public Object handleApplicationException(MethodArgumentNotValidException exception) {
        return handleException(exception);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({DomainException.class})
    public Object handleDomainExceptions(MethodArgumentNotValidException exception) {
        return handleException(exception);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(EntityNotFoundException.class)
    public Map<String, String> handleEntityNotFoundException(EntityNotFoundException exception) {
        return handleException(exception);
    }

    public Map<String, String> handleException(Exception exception) {

        final String message = exception.getMessage();

        final Map<String, String> result = new HashMap<>();
        result.put("message", message);

        return result;
    }
}
