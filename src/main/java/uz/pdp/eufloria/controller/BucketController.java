package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.dto.bucket.BucketResponseDto;
import uz.pdp.eufloria.service.BucketService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + BucketController.BASE_URL)
@RequiredArgsConstructor
public class BucketController {
    public static final String BASE_URL = "/bucket";
    private final BucketService service;

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @PutMapping("/{plantId}/{amount}")
    public ApiResponse<BucketResponseDto> addToBucket(@PathVariable UUID plantId, @PathVariable int amount) {
        return ApiResponse.body(service.toBucket(plantId, amount));
    }

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @DeleteMapping("/{plantId}")
    public ApiResponse<BucketResponseDto> removeFromBucket(@PathVariable UUID plantId) {
        return ApiResponse.body(service.removeFromBucket(plantId));
    }
}
