package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Role;

import java.util.UUID;

@Repository
public interface RoleRepository extends GenericRepository<Role, UUID> {
}
