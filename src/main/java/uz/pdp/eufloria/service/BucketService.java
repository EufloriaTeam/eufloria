package uz.pdp.eufloria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.common.CommonUtils;
import uz.pdp.eufloria.dto.bucket.BucketResponseDto;
import uz.pdp.eufloria.entity.Bucket;
import uz.pdp.eufloria.entity.BucketItem;
import uz.pdp.eufloria.entity.Plant;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.repository.BucketItemRepository;
import uz.pdp.eufloria.repository.BucketRepository;
import uz.pdp.eufloria.repository.PlantRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketRepository repository;
    private final BucketItemRepository bucketItemRepository;
    private final PlantRepository plantRepository;

    @Transactional
    public BucketResponseDto toBucket(UUID plantId, int amount) {
        Plant plant = plantRepository.findById(plantId).orElseThrow();
        User user = CommonUtils.getCurrentUser();

        Bucket bucket = getOrCreateBucket(user);

        BucketItem bucketItem = new BucketItem(plant, bucket, amount);
        bucketItemRepository.save(bucketItem);

        bucket.addItem(plant, amount);
        return repository.save(bucket).toResponseDto();
    }


    @Transactional
    public BucketResponseDto removeFromBucket(UUID plantId) {
        User user = CommonUtils.getCurrentUser();
        Bucket bucket = user.getBucket();
        if (Objects.isNull(bucket)) {
            throw ApiException.throwException("You have no bucket");
        }

        Plant plant = plantRepository.findById(plantId).orElseThrow(()-> ApiException.throwException("Plant not found"));
        BucketItem item = getPlantFromBucket(bucket, plant);
        bucket.getItems().remove(item);
        bucketItemRepository.deleteById(item.getBucketId());
        return bucket.toResponseDto();
    }

    private BucketItem getPlantFromBucket(Bucket bucket, Plant plant) {
        Optional<BucketItem> optItem = bucketItemRepository.findByBucketAndPlant(bucket, plant);
        if (optItem.isEmpty()) {
            throw ApiException.throwException("Item is not found in the bucket");
        }
        return optItem.get();
    }

    private Bucket getOrCreateBucket(User user) {
        return Objects.isNull(user.getBucket()) ? repository.save(new Bucket(user)) : user.getBucket();
    }
}
