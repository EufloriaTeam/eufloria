package uz.pdp.eufloria.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.dto.UserResponseDto;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.mapper.UserMapper;
import uz.pdp.eufloria.repository.UserRepository;
import uz.pdp.eufloria.rsql.SpecificationBuilder;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final UserMapper mapper;

    public UserResponseDto get(UUID id) {
        return mapper.toResponseDto(repository.findById(id)
                .orElseThrow(() -> ApiException.throwException("User not found")));
    }

    @Transactional
    public Page<UserResponseDto> getAll(String predicate, Pageable pageable) {
        Specification<User> specification = SpecificationBuilder.build(predicate);
        Page<User> page;
        page = specification == null ? repository.findAll(pageable) : repository.findAll(specification, pageable);
        return page.map(mapper::toResponseDto);
    }
}
