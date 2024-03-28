package uz.pdp.eufloria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import uz.pdp.eufloria.common.ApiResponse;
import uz.pdp.eufloria.common.AppConstants;
import uz.pdp.eufloria.dto.UserResponseDto;
import uz.pdp.eufloria.service.UserService;

import java.util.UUID;

@RestController
@RequestMapping(UserController.BASE_URL)
@RequiredArgsConstructor
public class UserController {
    public static final String BASE_URL = "/user";
    private final UserService service;

    @GetMapping("/{id}")
    public ApiResponse<UserResponseDto> get(@PathVariable UUID id) {
        return ApiResponse.body(service.get(id));
    }

    @GetMapping
    public Page<UserResponseDto> getAll(@RequestParam(required = false) String predicate, Pageable pageable){
        return service.getAll(predicate, pageable);
    }
}
