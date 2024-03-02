package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.dto.SignInDto;
import uz.pdp.eufloria.dto.SignUpDto;
import uz.pdp.eufloria.dto.UserResponseDto;
import uz.pdp.eufloria.service.AuthService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/sign-up")
    public ApiResponse<UserResponseDto> signUp(@RequestBody SignUpDto signUpDto) {
        return ApiResponse.body(authService.signUp(signUpDto));
    }

    @PostMapping("/sign-in")
    public ApiResponse<UserResponseDto> signIn(@RequestBody SignInDto signInDto) {
        return ApiResponse.body(authService.signIn(signInDto));
    }
}

