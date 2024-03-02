package uz.pdp.eufloria.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SignInDto {
    @NotNull
    private String email;
    // todo encode
    @NotNull
    private String password;
}
