package uz.pdp.eufloria.order.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Data;
import uz.pdp.eufloria.entity.enums.OrderStatus;
import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderResponseDto extends OrderDto{
    private UUID id;
    private OrderStatus status;
}
