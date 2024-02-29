package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.dto.ShippingDto;
import uz.pdp.eufloria.entity.Shipping;
import uz.pdp.eufloria.entity.enums.ShippingType;
import uz.pdp.eufloria.service.ShippingService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + ShippingController.BASE_URL)
@RequiredArgsConstructor
@CrossOrigin(origins = {"http://localhost:3000"})
public class ShippingController {
    public static final String BASE_URL = "/shipping";
    private final ShippingService service;

    @PostMapping
    public ApiResponse<Shipping> create(@RequestBody ShippingDto shippingDto) {
        return ApiResponse.body(service.create(shippingDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<Shipping> get(@PathVariable UUID id) {
        return ApiResponse.body(service.get(id));
    }

    @GetMapping
    public ApiResponse<Page<Shipping>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        return ApiResponse.body(service.getAll(predicate, pageable));
    }

    @GetMapping("/type")
    public ApiResponse<List<ShippingType>> getShippingTypes() {
        return ApiResponse.body((service.getTypes()));
    }

    @PutMapping("/{id}")
    public ApiResponse<Shipping> update(@PathVariable UUID id, @RequestBody ShippingDto shippingDto) {
        return ApiResponse.body(service.update(id, shippingDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.success();
    }
}
