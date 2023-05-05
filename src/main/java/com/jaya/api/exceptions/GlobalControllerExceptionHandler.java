package com.jaya.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity handleGenericExceptionServerError() {
        return ResponseEntity.notFound().build() ;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleGenericExceptionBadRequest(MethodArgumentNotValidException ex) {
        var errors = ex.getFieldErrors();
        return ResponseEntity.badRequest().body(errors.stream().map(DataErrorValidation::new).toList());
    }

    @ExceptionHandler
    public ResponseEntity handlerGlobalRuntimeException(GlobalRuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    private record DataErrorValidation(String field, String message) {
        public DataErrorValidation(FieldError error) {
            this(error.getField(), error.getDefaultMessage());
        }
    }


}

