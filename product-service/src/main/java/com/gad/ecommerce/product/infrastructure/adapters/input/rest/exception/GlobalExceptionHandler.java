package com.gad.ecommerce.product.infrastructure.adapters.input.rest.exception;

import com.gad.ecommerce.product.domain.exception.InsufficientStockException;
import com.gad.ecommerce.product.domain.exception.InvalidCategoryException;
import com.gad.ecommerce.product.domain.exception.InvalidProductException;
import com.gad.ecommerce.product.domain.exception.ProductNotFoundException;
import com.gad.ecommerce.product.infrastructure.adapters.input.rest.model.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(
            ConstraintViolationException ex,
            HttpServletRequest request) {

        List<String> errors = ex.getConstraintViolations()
                .stream()
                .map(violation -> {
                    String[] pathParts = violation.getPropertyPath().toString().split("\\.");
                    return pathParts[pathParts.length - 1] + ": " + violation.getMessage();
                })
                .sorted()
                .toList();

        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, "Validation incorrect", errors);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .sorted()
                .toList();

        return buildErrorResponse(request, HttpStatus.BAD_REQUEST, "Validation incorrect", errors);
    }

    @ExceptionHandler(ObjectOptimisticLockingFailureException.class)
    public ResponseEntity<ErrorResponse> handleObjectOptimisticLockingFailureException(HttpServletRequest request) {
        return buildErrorResponse(request, HttpStatus.CONFLICT, "Conflict of concurrence: The appeal was modified by another transaction.", null);
    }

    @ExceptionHandler(InvalidProductException.class)
    public ResponseEntity<ErrorResponse> handleInvalidProductException(InvalidProductException ex,
                                                                       HttpServletRequest request) {
        return  buildErrorResponse(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(InvalidCategoryException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCategoryException(InvalidCategoryException ex,
                                                                        HttpServletRequest request) {
        return  buildErrorResponse(request, HttpStatus.BAD_REQUEST, ex.getMessage(), null);
    }

    @ExceptionHandler(InsufficientStockException.class)
    public ResponseEntity<ErrorResponse> handleInsufficientStockException(InsufficientStockException ex,
                                                                          HttpServletRequest request) {
        return  buildErrorResponse(request, HttpStatus.CONFLICT, ex.getMessage(), null);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProductNotFoundException(ProductNotFoundException ex,
                                                                        HttpServletRequest request) {
        return  buildErrorResponse(request, HttpStatus.NOT_FOUND, ex.getMessage(), null);
    }

    public static ResponseEntity<ErrorResponse> buildErrorResponse(HttpServletRequest request,
                                                                   HttpStatus status,
                                                                   String message,
                                                                   List<String> errors) {

        String fullPath = request.getRequestURI();
        String query = request.getQueryString();
        String pathWithParams = query != null ? fullPath + "?" + query : fullPath;

        String methodHttp = request.getMethod();

        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .message(message)
                .errors(errors)
                .timestamp(LocalDateTime.now())
                .path(methodHttp + ": " + pathWithParams)
                .build();

        return ResponseEntity.status(status).body(errorResponse);
    }
}
