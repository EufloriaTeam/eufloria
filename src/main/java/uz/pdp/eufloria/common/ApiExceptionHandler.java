package uz.pdp.eufloria.common;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ApiResponse<?> handleException(ApiException e) {
        return ApiResponse.failResponse(e);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ApiResponse<?> handleException(EntityNotFoundException e) {
        return ApiResponse.failResponse(e.getMessage(), 400);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ApiResponse<?> handleException(DataIntegrityViolationException e) {
        return ApiResponse.failResponse(e.getMessage(), 500);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {
        return ApiResponse.failResponse(e.getMessage(), 500);
    }
}
