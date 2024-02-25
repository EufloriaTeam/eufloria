package uz.pdp.eufloria.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorData {
    private int errorCode = HttpStatus.BAD_REQUEST.value();
    private String msg;

    public ErrorData(String msg) {
        this.msg = msg;
    }
}
