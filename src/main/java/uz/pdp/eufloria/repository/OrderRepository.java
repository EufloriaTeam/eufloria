package uz.pdp.eufloria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.eufloria.entity.Order;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<Order, UUID> {
}
