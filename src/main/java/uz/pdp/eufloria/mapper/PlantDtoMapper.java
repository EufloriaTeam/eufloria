package uz.pdp.eufloria.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.dto.plantDto.PlantCreateDto;
import uz.pdp.eufloria.dto.plantDto.PlantResponseDto;
import uz.pdp.eufloria.dto.plantDto.PlantUpdateDto;
import uz.pdp.eufloria.entity.Plant;

@Component
@RequiredArgsConstructor
public class PlantDtoMapper extends GenericMapper<Plant, PlantCreateDto, PlantResponseDto,PlantUpdateDto> {
    private final ModelMapper modelMapper;
    @Override
    public Plant toEntity(PlantCreateDto plantCreateDto) {
        return modelMapper.map(plantCreateDto,Plant.class);
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
