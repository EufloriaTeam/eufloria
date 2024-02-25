package uz.pdp.eufloria.common;

import java.util.List;

public class ApiResponse<E> {
    private final boolean success;
    private String message;
    private E data;
    private List<ErrorData> errors;

    private ApiResponse(E data) {
        this.data = data;
        success = true;
    }

    private ApiResponse() {
        success = true;
    }

    private ApiResponse(List<ErrorData> errors) {
        this.errors = errors;
        success = false;
    }

    private ApiResponse(boolean isSuccess, String msg) {
        this.message = msg;
        this.success = isSuccess;
    }

    public static <T> ApiResponse<T> body(T data) {
        return new ApiResponse<>(data);
    }

    public static <T> ApiResponse<T> success() {
        return new ApiResponse<>();
    }

    public static <T> ApiResponse<T> respond(boolean isSuccess, String message) {
        return new ApiResponse<>(isSuccess,message);
    }

    public static ApiResponse<ErrorData> failResponse(String msg  , int errorCode) {
        return new ApiResponse<>(List.of(new ErrorData(errorCode, msg)));
    }
}
