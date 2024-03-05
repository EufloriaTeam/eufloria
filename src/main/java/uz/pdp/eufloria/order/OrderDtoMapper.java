package uz.pdp.eufloria.order;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.entity.Order;
import uz.pdp.eufloria.mapper.GenericMapper;
import uz.pdp.eufloria.order.dto.OrderDto;
import uz.pdp.eufloria.order.dto.OrderResponseDto;
import uz.pdp.eufloria.order.dto.OrderUpdateDto;

@Component
@RequiredArgsConstructor
public class OrderDtoMapper  extends GenericMapper<Order, OrderDto, OrderResponseDto, OrderUpdateDto> {
    private final ModelMapper modelMapper;
    @Override
    public Order toEntity(OrderDto orderDto) {
        return modelMapper.map(orderDto, Order.class);
    }

    @Override
    public OrderResponseDto toResponseDto(Order order) {
        return modelMapper.map(order, OrderResponseDto.class);
    }

    // bu toUpdate methodi bulishi kerak edi
    @Override
    public void toEntity(OrderUpdateDto orderUpdateDto, Order order) {
        modelMapper.map(orderUpdateDto,order);
    }
}
