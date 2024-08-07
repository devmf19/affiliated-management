package com.emssanareps.affiliate.manager.exception;

import com.emssanareps.affiliate.manager.constants.ExceptionConstants;
import com.emssanareps.affiliate.manager.dto.response.ResponseDto;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ResponseDto<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        ResponseDto<Map<String, String>> response = ResponseDto.<Map<String, String>>builder()
                .data(errors)
                .status(HttpStatus.NOT_ACCEPTABLE)
                .message(ExceptionConstants.ERROR)
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ResponseDto<String>> handleEntityNotFoundException(EntityNotFoundException ex) {
        ResponseDto<String> response = ResponseDto.<String>builder()
                .data(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDto<String>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        ResponseDto<String> response = ResponseDto.<String>builder()
                .data(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ResponseDto<String>> handleIllegalArgumentException(IllegalArgumentException ex) {
        ResponseDto<String> response = ResponseDto.<String>builder()
                .data(ExceptionConstants.VALIDATION_ERROR)
                .status(HttpStatus.BAD_REQUEST)
                .message(ex.getMessage())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseDto<String>> handleGenericException(Exception ex) {
        ResponseDto<String> response = ResponseDto.<String>builder()
                .data(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .message(ExceptionConstants.ERROR)
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
