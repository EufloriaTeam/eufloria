package uz.pdp.eufloria.dto.card;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardBaseDto {
    @NotNull
    private String name;
    @NotNull
    private String userName;
    @NotNull
    private LocalDate expirationDate;
    @NotNull
    private String number;
    @NotNull
    private String cvc;
}