package uz.pdp.eufloria.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddressDto {
    private String country;

    private String region;

    private String city;

    private String address;
}
