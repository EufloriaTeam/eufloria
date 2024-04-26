package uz.pdp.eufloria.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.dto.UserResponseDto;
import uz.pdp.eufloria.entity.User;

import java.io.ObjectInputStream;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class UserMapper {
    private final ModelMapper mapper;
    private final CardDtoMapper cardDtoMapper;
    private final PlantDtoMapper plantDtoMapper;

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        if (Objects.nonNull(user.getCards())) {
            responseDto.setCards(user.getCards().stream()
                    .map(cardDtoMapper::toResponseDto)
                    .collect(Collectors.toList()));
        }
        if (Objects.nonNull(user.getFavouritePlants())) {
            responseDto.setFavouritePlants(user.getFavouritePlants().stream()
                    .map(plantDtoMapper::toResponseDto)
                    .collect(Collectors.toList()));
        }
        return responseDto;
    }

}
