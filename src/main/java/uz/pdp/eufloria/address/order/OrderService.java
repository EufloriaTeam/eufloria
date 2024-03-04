package uz.pdp.eufloria.address.order;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.address.order.dto.OrderResponseDto;
import uz.pdp.eufloria.entity.BucketItem;
import uz.pdp.eufloria.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderResponseDto create(List<BucketItem.BucketId> plantIds, UUID shippingId) {
        return null;
    }
}
