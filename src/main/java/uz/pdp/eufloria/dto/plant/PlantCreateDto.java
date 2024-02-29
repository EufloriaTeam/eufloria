package uz.pdp.eufloria.dto.plant;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlantCreateDto {
    @NotNull
    private String name;
    private String description;
    private double price;
    private double discount;
}
