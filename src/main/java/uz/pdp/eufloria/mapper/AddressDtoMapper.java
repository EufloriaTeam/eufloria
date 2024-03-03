package uz.pdp.eufloria.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import uz.pdp.eufloria.dto.AddressDto;
import uz.pdp.eufloria.entity.Address;
import uz.pdp.eufloria.mapper.GenericMapper;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class AddressDtoMapper extends GenericMapper<Address, AddressDto, Address, AddressDto> {
    private final ModelMapper mapper;
    @Override
    public Address toEntity(AddressDto addressDto) {
        Address address = mapper.map(addressDto, Address.class);
        address.setId(UUID.randomUUID());
        return address;
    }
    @Override
    public Address toResponseDto(Address address) {
        return address;
    }

    @Override
    public void toEntity(AddressDto addressDto, Address address) {
        mapper.map(addressDto, address);
    }
}

