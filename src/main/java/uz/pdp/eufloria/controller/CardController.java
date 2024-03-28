package uz.pdp.eufloria.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.dto.card.CardCreateDto;
import uz.pdp.eufloria.dto.card.CardResponseDto;
import uz.pdp.eufloria.dto.card.CardUpdateDto;
import uz.pdp.eufloria.entity.Card;
import uz.pdp.eufloria.service.CardService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping(AppConstants.BASE_PATH + CardController.BASE_URL)
@RequiredArgsConstructor
public class CardController {
    public static final String BASE_URL = "/card";
    private final CardService service;

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @PostMapping
    public ApiResponse<CardResponseDto> create(@Valid @RequestBody CardCreateDto cardCreateDto) {
        CardResponseDto responseDto = service.create(cardCreateDto);
        return ApiResponse.body(responseDto);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping
    public ApiResponse<Page<CardResponseDto>> getAll(@RequestParam(required = false) String predicate, Pageable pageable) {
        Page<CardResponseDto> all = service.getAll(predicate, pageable);
        return ApiResponse.body(all);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping("/userCards")
    public ApiResponse<List<Card>> getCardsByUserId() {
        List<Card> cardsOfUser = service.getCardsOfUser();
        return ApiResponse.body(cardsOfUser);
    }

    @PreAuthorize("hasAuthority('ADMIN_ROLE')")
    @GetMapping("/{id}")
    public ApiResponse<CardResponseDto> getById(@PathVariable UUID id) {
        CardResponseDto responseDto = service.get(id);
        return ApiResponse.body(responseDto);
    }

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @PutMapping("/{id}")
    public ApiResponse<CardResponseDto> update(@PathVariable UUID id, @RequestBody CardUpdateDto cardUpdateDto) {
        CardResponseDto update = service.update(id, cardUpdateDto);
        return ApiResponse.body(update);
    }

    @PreAuthorize("hasAuthority('USER_ROLE')")
    @DeleteMapping("/{id}")
    public ApiResponse<CardResponseDto> delete(@PathVariable UUID id) {
        service.delete(id);
        return ApiResponse.respond(true, "Successfully deleted");
    }

}
