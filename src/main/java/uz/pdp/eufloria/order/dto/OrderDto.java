package uz.pdp.eufloria.order.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.entity.BucketItem;
import uz.pdp.eufloria.entity.Shipping;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {
    @NotNull
    private List<BucketItem> items;
    @NotNull
    private Shipping shipping;
}
