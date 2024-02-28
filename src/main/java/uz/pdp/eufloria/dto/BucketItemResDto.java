package uz.pdp.eufloria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.eufloria.entity.Plant;

@Data
@AllArgsConstructor
public class BucketItemResDto {
    private Plant plant;
    private int amount;
}
