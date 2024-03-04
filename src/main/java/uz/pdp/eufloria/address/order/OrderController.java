package uz.pdp.eufloria.address.order;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.address.order.dto.OrderResponseDto;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.entity.BucketItem;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + OrderController.BASE_URL)
@RequiredArgsConstructor
public class OrderController {
    public static final String BASE_URL = "/order";
    private final OrderService orderService;
    @PostMapping
    public ResponseEntity<OrderResponseDto> create(List<BucketItem.BucketId> plantIds, @PathVariable UUID shippingId){
        OrderResponseDto responseDto = orderService.create(plantIds, shippingId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }
}
