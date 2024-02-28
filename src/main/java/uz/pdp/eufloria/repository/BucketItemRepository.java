package uz.pdp.eufloria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.eufloria.entity.Bucket;
import uz.pdp.eufloria.entity.BucketItem;
import uz.pdp.eufloria.entity.Plant;

import java.util.Optional;

@Repository
public interface BucketItemRepository extends JpaRepository<BucketItem, BucketItem.BucketId> {
    Optional<BucketItem> findByBucketAndPlant(Bucket bucket, Plant plant);
}
