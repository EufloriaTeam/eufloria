package uz.pdp.eufloria.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.dto.plant.PlantCreateDto;
import uz.pdp.eufloria.dto.plant.PlantResponseDto;
import uz.pdp.eufloria.dto.plant.PlantUpdateDto;
import uz.pdp.eufloria.service.PlantService;

import java.util.UUID;

@RestController
@RequestMapping("/plant")
@RequiredArgsConstructor
public class PlantController {
    private final PlantService plantService;

    @PostMapping
    public ResponseEntity<PlantResponseDto> create(@RequestBody @Valid PlantCreateDto createDto) {
        PlantResponseDto responseDto = plantService.create(createDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<PlantResponseDto>> get(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<PlantResponseDto> all = plantService.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlantResponseDto> get(@PathVariable UUID id) {
        PlantResponseDto responseDto = plantService.get(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PlantResponseDto> update(@PathVariable UUID id, @RequestBody PlantUpdateDto updateDto) {
        PlantResponseDto responseDto = plantService.update(id, updateDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<PlantResponseDto> delete(@PathVariable UUID id) {
        plantService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
