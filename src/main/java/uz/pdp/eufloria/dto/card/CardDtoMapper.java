package uz.pdp.eufloria.dto.card;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.entity.Card;
import uz.pdp.eufloria.mapper.GenericMapper;

@Component
@RequiredArgsConstructor
public class CardDtoMapper extends GenericMapper<Card, CardCreateDto, CardResponseDto, CardUpdateDto> {
    private final ModelMapper mapper;

    @Override
    public Card toEntity(CardCreateDto cardCreateDto) {
        return mapper.map(cardCreateDto, Card.class);
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
