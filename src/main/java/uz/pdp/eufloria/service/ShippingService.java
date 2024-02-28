package uz.pdp.eufloria.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.dto.ShippingDto;
import uz.pdp.eufloria.entity.Shipping;
import uz.pdp.eufloria.entity.enums.ShippingType;
import uz.pdp.eufloria.mapper.ShippingMapper;
import uz.pdp.eufloria.repository.ShippingRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Getter
@RequiredArgsConstructor
public class ShippingService extends GenericService<Shipping, UUID, Shipping, ShippingDto, ShippingDto> {
    private final ShippingRepository repository;
    private final Class<Shipping> entityClass = Shipping.class;
    private final ShippingMapper mapper;

    @Override
    protected Shipping internalCreate(ShippingDto shippingDto) {
        isValid(shippingDto);
        return repository.save(mapper.toEntity(shippingDto));
    }

    @Override
    protected Shipping internalUpdate(UUID id, ShippingDto shippingDto) {
        isValid( shippingDto);
        Shipping enShipping = get(id);
        mapper.toEntity(shippingDto, enShipping);
        return repository.save(enShipping);
    }

    private void isValid(ShippingDto shippingDto) {
        List<String> errorMessages = new LinkedList<>();
        if (Objects.isNull(shippingDto))
            throw ApiException.throwException("Shipping cannot be null");

        if (Objects.isNull(shippingDto.getName()))
            errorMessages.add("Name cannot be null");
        else if (shippingDto.getName().isBlank())
            errorMessages.add("Name cannot be blank");

        if (Objects.isNull(shippingDto.getType()))
            errorMessages.add("Type cannot be null");
        else if (shippingDto.getType().isBlank())
            errorMessages.add("Type cannot be blank");
        else if (!isTypeFound(shippingDto.getType()))
            errorMessages.add("Wrong type of shipping");

        if (!errorMessages.isEmpty())
            throw ApiException.throwException(errorMessages);
    }

    private boolean isTypeFound(String type) {
        boolean typeFound = false;
        for (var shippingType : ShippingType.values())
            if (Objects.equals(shippingType.toString(), type)) {
                typeFound = true;
                break;
            }
        return typeFound;
    }
}
