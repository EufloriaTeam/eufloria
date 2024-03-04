package uz.pdp.eufloria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.dto.SignInDto;
import uz.pdp.eufloria.dto.SignUpDto;
import uz.pdp.eufloria.dto.UserResponseDto;
import uz.pdp.eufloria.entity.Address;
import uz.pdp.eufloria.entity.Card;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.mapper.AuthMapper;
import uz.pdp.eufloria.repository.AddressRepository;
import uz.pdp.eufloria.repository.CardRepository;
import uz.pdp.eufloria.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthMapper authMapper;
    private final AddressRepository addressRepository;
    private final CardRepository cardRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponseDto signUp(SignUpDto signUpDto) {
        User user = authMapper.toUser(signUpDto);
        checkIfExists(signUpDto);
        setAddress(signUpDto, user);
        setCard(signUpDto, user);
        return authMapper.toResponseDto(userRepository.save(user));
    }


    public UserResponseDto signIn(SignInDto signInDto) {
        Optional<User> optUser = getByEmail(signInDto.getEmail());
        if (optUser.isEmpty())
            throw ApiException.throwException("Email or password is incorrect");

        if (!passwordEncoder.matches(signInDto.getPassword(), optUser.get().getPassword())) {
            throw ApiException.throwException("Email or password is incorrect");
        }
        return authMapper.toResponseDto(optUser.get());
    }

    private void checkIfExists(SignUpDto signUpDto) {
        if (userRepository.existsByEmail(signUpDto.getEmail()))
            throw ApiException.throwException("An account with such email already exists");
    }

    private Optional<User> getByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void setCard(SignUpDto signUpDto, User user) {
        if (Objects.nonNull(signUpDto.getCards()) && !signUpDto.getCards().isEmpty()) {
            List<Card> cards = signUpDto.getCards()
                    .stream().map((a) ->
                            cardRepository.findById(a).orElseThrow(
                                    () -> ApiException.throwException("Card not found"))).toList();
            user.setCards(cards);
        }
    }

    private void setAddress(SignUpDto signUpDto, User user) {
        if (Objects.nonNull(signUpDto.getAddresses()) && !signUpDto.getAddresses().isEmpty()) {
            List<Address> addresses = signUpDto.getAddresses()
                    .stream().map((a) ->
                            addressRepository.findById(a).orElseThrow(
                                    () -> ApiException.throwException("Address not found"))).toList();
            user.setAddresses(addresses);
        }
    }
}
