package uz.pdp.eufloria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.PostOffice;

@Repository
public interface PostOfficeRepository extends JpaRepository<PostOffice, String> {
}
