package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.User;

import java.util.UUID;

@Repository
public interface UserRepository extends GenericRepository<User, UUID> {
}
