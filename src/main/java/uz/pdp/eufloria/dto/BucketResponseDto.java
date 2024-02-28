package uz.pdp.eufloria.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class BucketResponseDto {
    private List<BucketItemResDto> plants;
}
