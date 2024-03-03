package uz.pdp.eufloria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.dto.card.CardResponseDto;
import uz.pdp.eufloria.entity.Address;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponseDto {
    private String name;
    private String email;
    private String password;
    private String token;
    private List<CardResponseDto> cards;
    private List<Address> addresses;
}
