package uz.pdp.eufloria.order;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.entity.BucketItem;
import uz.pdp.eufloria.order.dto.OrderResponseDto;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + OrderController.BASE_URL)
@RequiredArgsConstructor
public class OrderController {
    public static final String BASE_URL = "/order";
    private final OrderService orderService;

    @PostMapping("/{shippingId}")
    public ResponseEntity<OrderResponseDto> create(@RequestBody List<BucketItem.BucketId> plantIds, @PathVariable UUID shippingId){
        OrderResponseDto responseDto = orderService.create(plantIds, shippingId);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> get(@PathVariable UUID orderId){
        OrderResponseDto responseDto = orderService.get(orderId);
        return ResponseEntity.ok(responseDto);
    }
    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> get(@RequestParam(required = false) String predicate, Pageable pageable){
        Page<OrderResponseDto> all = orderService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }
    @PutMapping("/{orderId}")
    public ResponseEntity<OrderResponseDto> update(@PathVariable UUID orderId, @RequestBody @Valid String status) {
        OrderResponseDto responseDto = orderService.update(orderId, status);
        return ResponseEntity.ok(responseDto);
    }


}
