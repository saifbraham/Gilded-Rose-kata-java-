package com.gildedrose.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebInputException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ValidationExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ValidationExceptionHandler.class);

    /**
     * Handles validation exceptions for field-level errors.
     */
    @ExceptionHandler({MethodArgumentNotValidException.class, WebExchangeBindException.class, ServerWebInputException.class})
    public ResponseEntity<Map<String, String>> handleValidationExceptions(Exception ex) {
        Map<String, String> errors = extractFieldErrors(ex);

        // If no specific field errors are found, provide a generic error for ServerWebInputException
        if (errors.isEmpty() && ex instanceof ServerWebInputException) {
            logger.error("Validation Error: Invalid input provided");
            errors.put("error", "Invalid input provided. Please check the request body or parameters.");
        }

        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Handles illegal argument exceptions.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleIllegalArgumentExceptions(IllegalArgumentException ex) {
        logger.error("Validation Error: {}", ex.getMessage());
        return ResponseEntity.badRequest().body(Map.of("error", ex.getMessage()));
    }

    /**
     * Extracts field errors from validation exceptions.
     */
    private Map<String, String> extractFieldErrors(Exception ex) {
        Map<String, String> errors = new HashMap<>();
        BindingResult bindingResult = null;

        if (ex instanceof MethodArgumentNotValidException) {
            bindingResult = ((MethodArgumentNotValidException) ex).getBindingResult();
        } else if (ex instanceof WebExchangeBindException) {
            bindingResult = ((WebExchangeBindException) ex).getBindingResult();
        }

        if (bindingResult != null) {
            bindingResult.getFieldErrors().forEach(error -> {
                logger.error("Validation Error: {} - {}", error.getField(), error.getDefaultMessage());
                errors.put(error.getField(), error.getDefaultMessage());
            });
        }

        return errors;
    }
}
