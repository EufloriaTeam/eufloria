package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Image;

import java.util.UUID;

@Repository
public interface ImageRepository extends GenericRepository<Image, UUID> {
}
