package uz.pdp.eufloria.dto.plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlantResponseDto {
    private String name;

    private String description;

    private double price;

    private double discount;
}
