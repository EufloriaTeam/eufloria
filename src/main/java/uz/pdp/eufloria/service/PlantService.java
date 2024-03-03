package uz.pdp.eufloria.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.dto.plant.PlantCreateDto;
import uz.pdp.eufloria.dto.plant.PlantResponseDto;
import uz.pdp.eufloria.dto.plant.PlantUpdateDto;
import uz.pdp.eufloria.entity.Plant;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.mapper.PlantDtoMapper;
import uz.pdp.eufloria.repository.PlantRepository;
import uz.pdp.eufloria.security.UserPrincipal;

import java.util.List;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class PlantService extends GenericService<Plant, UUID, PlantResponseDto, PlantCreateDto, PlantUpdateDto> {
    private final PlantRepository repository;
    private final Class<Plant> entityClass = Plant.class;
    private final PlantDtoMapper mapper;

    @Override
    protected PlantResponseDto internalCreate(PlantCreateDto plantCreateDto) {
        UserPrincipal principal = (UserPrincipal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = principal.getUser();
        user.getFavouritePlants();
        // getFavourites()

        Plant plant = mapper.toEntity(plantCreateDto);
        plant.setId(UUID.randomUUID());
        Plant saved = repository.save(plant);
        return mapper.toResponseDto(saved);
    }

    @Override
    protected PlantResponseDto internalUpdate(UUID uuid, PlantUpdateDto plantUpdateDto) {
        Plant plant = repository.findById(uuid)
                .orElseThrow(
                        () -> new EntityNotFoundException("Plant not found")
                );
        mapper.toEntity(plantUpdateDto, plant);
        Plant saved = repository.save(plant);
        return mapper.toResponseDto(saved);
    }
}
