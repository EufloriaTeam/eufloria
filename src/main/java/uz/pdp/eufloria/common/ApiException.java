package uz.pdp.eufloria.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Getter
public class ApiException extends RuntimeException {
    private HttpStatus status = HttpStatus.BAD_REQUEST;
    private List<ErrorData> errors = new LinkedList<>();

    private ApiException(String message, HttpStatus status) {
        super(message);
        this.errors.add(new ErrorData(message));
        this.status = status;
    }

    private ApiException(String message) {
        super(message);
        this.errors.add(new ErrorData(message));
    }

    private ApiException(List<String> messages) {
        this.errors = messages.stream().map(ErrorData::new).collect(Collectors.toList());
    }

    public static ApiException throwException(List<String> messages) {
        return new ApiException(messages);
    }

    public static ApiException throwException(String message) {
        return new ApiException(message);
    }

    public static ApiException throwException(String message, HttpStatus status) {
        return new ApiException(message, status);
    }
}
