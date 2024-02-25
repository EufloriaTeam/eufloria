package uz.pdp.eufloria.repository;

import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Payment;

import java.util.UUID;

@Repository
public interface PaymentRepository extends GenericRepository<Payment, UUID> {
}
