package uz.pdp.eufloria.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.dto.plant.PlantCreateDto;
import uz.pdp.eufloria.dto.plant.PlantResponseDto;
import uz.pdp.eufloria.dto.plant.PlantUpdateDto;
import uz.pdp.eufloria.service.PlantService;

import java.util.UUID;

@RestController
@RequestMapping(AppConstants.BASE_PATH + PlantController.BASE_URL)
@RequiredArgsConstructor
public class PlantController {
    public static final String BASE_URL = "/plant";
    private final PlantService plantService;

    @PostMapping
    public ApiResponse<PlantResponseDto> create(@RequestBody @Valid PlantCreateDto createDto) {
        PlantResponseDto responseDto = plantService.create(createDto);
        return ApiResponse.body(responseDto);
    }

    @GetMapping
    public ApiResponse<Page<PlantResponseDto>> get(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<PlantResponseDto> all = plantService.getAll(predicate, pageable);
        return ApiResponse.body(all);
    }

    @GetMapping("/{id}")
    public ApiResponse<PlantResponseDto> get(@PathVariable UUID id) {
        PlantResponseDto responseDto = plantService.get(id);
        return ApiResponse.body(responseDto);
    }

    @PutMapping("/{id}")
    public ApiResponse<PlantResponseDto> update(@PathVariable UUID id, @RequestBody @Valid PlantUpdateDto updateDto) {
        PlantResponseDto responseDto = plantService.update(id, updateDto);
        return ApiResponse.body(responseDto);
    }

    @DeleteMapping("/{id}")
    public ApiResponse<PlantResponseDto> delete(@PathVariable UUID id) {
        plantService.delete(id);
        return ApiResponse.respond(true, "Successfully deleted");
    }
}
