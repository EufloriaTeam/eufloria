package uz.pdp.eufloria.dto.plant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.entity.Image;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PlantResponseDto {
    private UUID id;

    private String name;

    private String description;

    private double price;

    private double discount;

    private Image image;
}
