package uz.pdp.eufloria.address;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.address.dto.AddressDto;
import uz.pdp.eufloria.address.dto.AddressDtoMapper;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.entity.Address;
import uz.pdp.eufloria.repository.AddressRepository;
import uz.pdp.eufloria.service.GenericService;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class AddressService extends GenericService<Address, UUID, Address, AddressDto, AddressDto> {
    private final AddressRepository repository;
    private final Class<Address> entityClass = Address.class;
    private final AddressDtoMapper mapper;

    @Override
    protected Address internalCreate(AddressDto addressDto) {
        isValid(addressDto);
        return repository.save(mapper.toEntity(addressDto));
    }

    @Override
    protected Address internalUpdate(UUID id, AddressDto addressDto) {
        isValid( addressDto);
        Address address = get(id);
        mapper.toUpdate(addressDto, address);
        return repository.save(address);
    }

    private void isValid(AddressDto addressDto) {
        List<String> errorMessages = new LinkedList<>();
        if (Objects.isNull(addressDto))
            throw ApiException.throwException("Address cannot be null");

        if (Objects.isNull(addressDto.getCountry()))
            errorMessages.add("Country cannot be null");
        else if (addressDto.getCountry().isBlank())
            errorMessages.add("Country cannot be blank");

        if (Objects.isNull(addressDto.getRegion()))
            errorMessages.add("Region cannot be null");
        else if (addressDto.getRegion().isBlank())
            errorMessages.add("Region cannot be blank");

        if (Objects.isNull(addressDto.getCity()))
            errorMessages.add("City cannot be null");
        else if (addressDto.getCity().isBlank())
            errorMessages.add("City cannot be blank");

        if (Objects.isNull(addressDto.getAddress()))
            errorMessages.add("Address cannot be null");
        else if (addressDto.getAddress().isBlank())
            errorMessages.add("Address cannot be blank");

        if (!errorMessages.isEmpty())
            throw ApiException.throwException(errorMessages);
    }
}
