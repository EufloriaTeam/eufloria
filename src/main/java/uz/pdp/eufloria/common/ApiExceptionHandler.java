package uz.pdp.eufloria.common;

import io.jsonwebtoken.io.DecodingException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.LazyInitializationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ApiExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    public ApiExceptionHandler() {
        logger.info("ApiExceptionHandler initiated");
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ApiResponse<?>> handleException(ApiException e) {
        ApiResponse<ErrorData> response = ApiResponse.failResponse(e);
        return new ResponseEntity<>(response, e.getStatus());
    }

    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleException(NoResourceFoundException e) {
        return new ResponseEntity<>(ApiResponse.respond(false, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleException(NoHandlerFoundException e) {
        return new ResponseEntity<>(ApiResponse.respond(false, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ApiResponse<?>> handleException(EntityNotFoundException e) {
        ApiResponse<ErrorData> response = ApiResponse.respond(false, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<?>> handleException(DataIntegrityViolationException e) {
        ApiResponse<ErrorData> response = ApiResponse.respond(false, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<?>> handleException(MethodArgumentNotValidException e) {
        ApiResponse<ErrorData> response = ApiResponse.respond(false, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResponse<?>> handleException(AccessDeniedException e) {
        ApiResponse<Object> response = ApiResponse.respond(false, e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<ApiResponse<?>> handleException(DecodingException e) {
        return new ResponseEntity<>(ApiResponse.respond(false, e.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(LazyInitializationException.class)
    public ResponseEntity<ApiResponse<?>> handleException(LazyInitializationException e) {
        return new ResponseEntity<>(ApiResponse.respond(false, e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiResponse<?>> handleException(NoSuchElementException e) {
        return new ResponseEntity<>(ApiResponse.respond(false, e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<?>> handleException(HttpRequestMethodNotSupportedException e) {
        return new ResponseEntity<>(ApiResponse.respond(false, e.getMessage()), HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleException(Exception e) {
        return new ResponseEntity<>(
                ApiResponse.respond(false, e.getMessage()),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

}
