package uz.pdp.eufloria.order.dto;

import lombok.EqualsAndHashCode;
import uz.pdp.eufloria.entity.enums.OrderStatus;

@EqualsAndHashCode(callSuper = true)
public class OrderResponseDto extends OrderDto{
    private OrderStatus status;
}
