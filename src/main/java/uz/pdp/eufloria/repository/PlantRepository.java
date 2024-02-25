package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Plant;

import java.util.UUID;

@Repository
public interface PlantRepository extends GenericRepository<Plant, UUID> {
}
