package uz.pdp.eufloria.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.dto.PostOfficeDto;
import uz.pdp.eufloria.entity.Address;
import uz.pdp.eufloria.entity.PostOffice;
import uz.pdp.eufloria.mapper.AddressDtoMapper;
import uz.pdp.eufloria.repository.AddressRepository;
import uz.pdp.eufloria.repository.PostOfficeRepository;
import uz.pdp.eufloria.rsql.SpecificationBuilder;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class PostOfficeService {
    private final PostOfficeRepository repository;
    private final AddressRepository addressRepository;
    private final AddressDtoMapper addressDtoMapper;
    private final ModelMapper mapper;

    @Transactional
    public PostOffice create(PostOfficeDto postOfficeDto) {
        PostOffice entity = mapper.map(postOfficeDto, PostOffice.class);
        Address saved = addressRepository.save(addressDtoMapper.toEntity(postOfficeDto.getAddress()));
        entity.setAddress(saved);
        entity = repository.save(entity);
        return entity;
    }

    @Transactional
    public PostOffice get(String id) {
        return repository.findById(id)
                .orElseThrow(() -> ApiException.throwException("Post office not found"));
    }

    @Transactional
    public Page<PostOffice> getAll(String predicate, Pageable pageable) {
        Specification<PostOffice> specification = SpecificationBuilder.build(predicate);
        Page<PostOffice> page;
        page = specification == null ? repository.findAll(pageable) : repository.findAll(specification, pageable);
        return page;
    }


}
