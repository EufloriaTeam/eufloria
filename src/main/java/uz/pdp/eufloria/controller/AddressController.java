package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.dto.AddressDto;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.entity.Address;
import uz.pdp.eufloria.service.AddressService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + AddressController.BASE_URL)
@RequiredArgsConstructor
public class AddressController {
    public static final String BASE_URL = "/address";
    private final AddressService service;
    @PostMapping
    public ApiResponse<Address> create(@RequestBody AddressDto addressDto) {
        return ApiResponse.body(service.create(addressDto));
    }

    @GetMapping("/{id}")
    public ApiResponse<Address> get(@PathVariable UUID id) {
        return ApiResponse.body(service.get(id));
    }

    @GetMapping
    public ApiResponse<Page<Address>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        return ApiResponse.body(service.getAll(predicate, pageable));
    }

    @PutMapping("/{id}")
    public ApiResponse<Address> update(@PathVariable UUID id, @RequestBody AddressDto addressDto) {
        return ApiResponse.body(service.update(id, addressDto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.success();
    }
}
