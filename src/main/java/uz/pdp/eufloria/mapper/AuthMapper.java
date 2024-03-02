package uz.pdp.eufloria.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.dto.SignUpDto;
import uz.pdp.eufloria.dto.UserResponseDto;
import uz.pdp.eufloria.entity.Role;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.security.JwtTokenProvider;

import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AuthMapper {
    private final JwtTokenProvider tokenProvider;
    private final CardDtoMapper cardDtoMapper;
    private final ModelMapper mapper;
    private final PasswordEncoder passwordEncoder;

    public User toUser(SignUpDto signUpDto) {
        User user = mapper.map(signUpDto, User.class);
        user.setId(UUID.randomUUID());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));
        user.setRole(new Role("USER_ROLE"));
        return user;
    }

    public UserResponseDto toResponseDto(User user) {
        UserResponseDto responseDto = mapper.map(user, UserResponseDto.class);
        if (Objects.nonNull(user.getCards())) {
            responseDto.setCards(
                    user.getCards()
                            .stream()
                            .map(cardDtoMapper::toResponseDto)
                            .collect(Collectors.toList()));
        }
        responseDto.setToken(tokenProvider.generateToken(user.getId()));
        return responseDto;
    }
}
