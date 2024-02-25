package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Address;

import java.util.UUID;

@Repository
public interface AddressRepository extends GenericRepository<Address, UUID> {
}
