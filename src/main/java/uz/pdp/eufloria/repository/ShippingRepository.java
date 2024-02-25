package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Shipping;

import java.util.UUID;

@Repository
public interface ShippingRepository extends GenericRepository<Shipping, UUID> {
}
