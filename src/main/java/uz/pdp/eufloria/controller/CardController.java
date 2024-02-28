package uz.pdp.eufloria.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.dto.card.CardCreateDto;
import uz.pdp.eufloria.dto.card.CardResponseDto;
import uz.pdp.eufloria.dto.card.CardUpdateDto;
import uz.pdp.eufloria.service.CardService;

import java.util.UUID;


@RestController
@RequestMapping("/card")
@RequiredArgsConstructor
public class CardController {
    private final CardService service;

    @PostMapping
    public ResponseEntity<CardResponseDto> create(@Valid @RequestBody CardCreateDto cardCreateDto) {
        CardResponseDto responseDto = service.create(cardCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping
    public ResponseEntity<Page<CardResponseDto>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<CardResponseDto> all = service.getAll(predicate, pageable);
        return ResponseEntity.ok(all);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CardResponseDto> getById(@PathVariable UUID id) {
        CardResponseDto responseDto = service.get(id);
        return ResponseEntity.ok(responseDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CardResponseDto> update(@PathVariable UUID id, @RequestBody CardUpdateDto cardUpdateDto) {
        CardResponseDto update = service.update(id, cardUpdateDto);
        return ResponseEntity.ok(update);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CardResponseDto> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
