package uz.pdp.eufloria.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Getter
public class ApiException extends RuntimeException {
    private final String message;
    private HttpStatus status = HttpStatus.BAD_REQUEST;

    private ApiException(String message, HttpStatus status) {
        super(message);
        this.message = message;
        this.status = status;
    }

    private ApiException(String message){
        super(message);
        this.message = message;
    }

    public static ApiException throwException(String message) {
        return new ApiException(message);
    }

    public static ApiException throwException(String message, HttpStatus status) {
        return new ApiException(message, status);
    }
}
