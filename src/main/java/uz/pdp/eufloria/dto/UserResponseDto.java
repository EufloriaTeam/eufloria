package uz.pdp.eufloria.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.dto.card.CardResponseDto;
import uz.pdp.eufloria.dto.plant.PlantResponseDto;
import uz.pdp.eufloria.entity.Address;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class UserResponseDto {
    private String name;
    private String email;
    private String password;
    private String token;
    private List<PlantResponseDto> favouritePlants;
    // todo add orders
    private List<CardResponseDto> cards;
    private List<Address> addresses;
}
