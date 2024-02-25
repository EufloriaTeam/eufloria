package uz.pdp.eufloria.common;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ApiResponse<?> handleException(ApiException e) {
        return ApiResponse.failResponse(e.getMessage(), e.getStatus().value());
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleException(Exception e) {
        return ApiResponse.failResponse(e.getMessage(), 500);
    }
}
