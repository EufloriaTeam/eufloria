package uz.pdp.eufloria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Plant;

import java.util.UUID;

@Repository
public interface PlantRepository extends JpaRepository<Plant, UUID> {
}
