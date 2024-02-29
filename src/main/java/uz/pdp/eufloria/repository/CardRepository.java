package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Card;

import java.util.UUID;

@Repository
public interface CardRepository extends GenericRepository<Card, UUID> {
    boolean existsByNumber(String number);
}
