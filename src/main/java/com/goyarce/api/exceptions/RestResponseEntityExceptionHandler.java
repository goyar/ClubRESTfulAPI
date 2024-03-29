package com.goyarce.api.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler
        extends ResponseEntityExceptionHandler {

    @ExceptionHandler(
        value = {
            IllegalArgumentException.class,
            IllegalStateException.class
        }
    )
    protected ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "This should be application specific";
        System.out.println(bodyOfResponse);
        return handleExceptionInternal(ex, bodyOfResponse, new HttpHeaders(), HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(
            value = {
                    ResourceNotFoundException.class
            }
    )
    protected ResponseEntity<Object> handleResourceNotFound(RuntimeException ex, WebRequest request) {
        ApiError error = new ApiError(HttpStatus.NOT_FOUND, ex.getMessage());
        String bodyOfResponse = ex.getMessage();
        System.out.println(bodyOfResponse);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(
            value = {
                    InconsistentArgumentsException.class
            }
    )
    protected ResponseEntity<Object> handleArgumentsNotConsistent(RuntimeException ex, WebRequest request) {
        ApiError error = new ApiError(HttpStatus.BAD_REQUEST, ex.getMessage());
        String bodyOfResponse = ex.getMessage();
        System.out.println(bodyOfResponse);
        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
