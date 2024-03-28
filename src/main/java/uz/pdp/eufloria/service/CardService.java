package uz.pdp.eufloria.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.common.CommonUtils;
import uz.pdp.eufloria.dto.card.CardCreateDto;
import uz.pdp.eufloria.dto.card.CardResponseDto;
import uz.pdp.eufloria.dto.card.CardUpdateDto;
import uz.pdp.eufloria.entity.Card;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.entity.enums.CardType;
import uz.pdp.eufloria.mapper.CardDtoMapper;
import uz.pdp.eufloria.repository.CardRepository;
import uz.pdp.eufloria.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class CardService extends GenericService<Card, UUID, CardResponseDto, CardCreateDto, CardUpdateDto> {
    private final CardRepository repository;
    private final UserRepository userRepository;
    private static final Random RANDOM = new Random();
    private final Class<Card> EntityClass = Card.class;
    private final CardDtoMapper mapper;

    @Override
    @Transactional
    public CardResponseDto internalCreate(CardCreateDto cardCreateDto) {
        Card card = mapper.toEntity(cardCreateDto);
        if (repository.existsByNumber(card.getNumber())) {
            throw ApiException.throwException("Number already exist");
        }
        if (card.getNumber().length() != 16) {
            throw ApiException.throwException("Invalid card number entered");
        }

        User user = CommonUtils.getCurrentUser();

        setType(cardCreateDto, card);
        Card savedCard = repository.save(card);
        user.getCards().add(savedCard);
        userRepository.save(user);
        return mapper.toResponseDto(savedCard);
    }


    @Override
    @Transactional
    public CardResponseDto internalUpdate(UUID uuid, CardUpdateDto cardUpdateDto) {
        Card card = repository.findById(uuid).orElseThrow(() -> new EntityNotFoundException("Card with id %s not found".formatted(uuid)));

        mapper.toEntity(cardUpdateDto, card);

        Card saved = repository.save(card);
        return mapper.toResponseDto(saved);
    }

    private void setType(CardCreateDto cardCreateDto, Card entity) {
        String typeUpper = cardCreateDto.getType().toUpperCase();
        boolean isFound = false;
        for (var type : CardType.values()) {
            if (typeUpper.equals(type.name())) {
                isFound = true;
                break;
            }
        }
        if (!isFound) {
            throw ApiException.throwException("Type is not found");
        }
        entity.setType(CardType.valueOf(typeUpper));
    }

    @Transactional
    public List<Card> getCardsOfUser() {
        User user = CommonUtils.getCurrentUser();

        if (user.getCards().isEmpty()) {
            return List.of();
        }
        return user.getCards();
    }
    protected Card subtract(UUID cardId, double sum) {
        User user = CommonUtils.getCurrentUser();
        Card card = null;
        for (var c : user.getCards()) {
            if (Objects.equals(c.getId(), cardId)) {
                card = c;
                break;
            }
        }
        if (Objects.isNull(card))
            throw ApiException.throwException("Something went wrong");

        double balance = card.getBalance();
        if (balance >= sum) {
            card.setBalance(balance - sum);
            return repository.save(card);
        }
        else
            throw ApiException.throwException("Insufficient funds");
    }

}
