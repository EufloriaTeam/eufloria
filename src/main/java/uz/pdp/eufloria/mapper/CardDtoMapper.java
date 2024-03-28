package uz.pdp.eufloria.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.dto.card.CardCreateDto;
import uz.pdp.eufloria.dto.card.CardResponseDto;
import uz.pdp.eufloria.dto.card.CardUpdateDto;
import uz.pdp.eufloria.entity.Card;
import uz.pdp.eufloria.mapper.GenericMapper;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class CardDtoMapper extends GenericMapper<Card, CardCreateDto, CardResponseDto, CardUpdateDto> {
    private final ModelMapper mapper;

    @Override
    public Card toEntity(CardCreateDto cardCreateDto) {
        Card card = mapper.map(cardCreateDto, Card.class);
        card.setId(UUID.randomUUID());
        return card;
    }

    @Override
    public CardResponseDto toResponseDto(Card card) {
        return mapper.map(card, CardResponseDto.class);
    }

    @Override
    public void toEntity(CardUpdateDto cardUpdateDto, Card card) {
        mapper.map(cardUpdateDto, card);
    }

}
