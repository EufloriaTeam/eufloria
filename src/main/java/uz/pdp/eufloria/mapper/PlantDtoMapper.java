package uz.pdp.eufloria.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.dto.plant.PlantCreateDto;
import uz.pdp.eufloria.dto.plant.PlantResponseDto;
import uz.pdp.eufloria.dto.plant.PlantUpdateDto;
import uz.pdp.eufloria.entity.Plant;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PlantDtoMapper extends GenericMapper<Plant, PlantCreateDto, PlantResponseDto,PlantUpdateDto> {
    private final ModelMapper modelMapper;
    @Override
    public Plant toEntity(PlantCreateDto plantCreateDto) {
        Plant plant = modelMapper.map(plantCreateDto, Plant.class);
        plant.setId(UUID.randomUUID());
        return plant;
    }

    @Override
    public PlantResponseDto toResponseDto(Plant plant) {
        return modelMapper.map(plant, PlantResponseDto.class);
    }

    @Override
    public void toEntity(PlantUpdateDto plantUpdateDto, Plant plant) {
    modelMapper.map(plantUpdateDto,plant);
    }
}
