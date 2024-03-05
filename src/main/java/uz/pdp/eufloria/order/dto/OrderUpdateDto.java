package uz.pdp.eufloria.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import uz.pdp.eufloria.entity.enums.OrderStatus;

@Data
@AllArgsConstructor
public class OrderUpdateDto {
    private OrderStatus status;
}
