package uz.pdp.eufloria.dto.card;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponseDto extends CardBaseDto {
    protected UUID id;
    private String cvc;
    private String type;
    private double balance;
}
