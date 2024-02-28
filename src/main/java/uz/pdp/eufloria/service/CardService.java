package uz.pdp.eufloria.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.dto.card.CardCreateDto;
import uz.pdp.eufloria.dto.card.CardDtoMapper;
import uz.pdp.eufloria.dto.card.CardResponseDto;
import uz.pdp.eufloria.dto.card.CardUpdateDto;
import uz.pdp.eufloria.entity.Card;
import uz.pdp.eufloria.repository.CardRepository;

import java.util.Random;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class CardService extends GenericService<Card, UUID, CardResponseDto, CardCreateDto, CardUpdateDto> {
    private final CardRepository repository;
    private static final Random RANDOM = new Random();
    private final Class<Card> entityClass = Card.class;
    private final CardDtoMapper mapper;

    @Override
    protected CardResponseDto internalCreate(CardCreateDto cardCreateDto) {
        Card entity = mapper.toEntity(cardCreateDto);
        entity.setId(UUID.randomUUID());
        entity.setCvc(getCvc());
        Card savedCard = repository.save(entity);
        return mapper.toResponseDto(savedCard);
    }

    @Override
    protected CardResponseDto internalUpdate(UUID uuid, CardUpdateDto cardUpdateDto) {
        Card card = repository.findById(uuid).orElseThrow(
                () -> new EntityNotFoundException("Card with id %s not found".formatted(uuid)));


        mapper.toEntity(cardUpdateDto, card);

        Card saved = repository.save(card);
        return mapper.toResponseDto(saved);
    }

    private String getCvc() {
        return String.valueOf(RANDOM.nextInt(100, 999));
    }

}
