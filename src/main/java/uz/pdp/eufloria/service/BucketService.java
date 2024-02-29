package uz.pdp.eufloria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.pdp.eufloria.common.ApiException;
import uz.pdp.eufloria.dto.bucket.BucketResponseDto;
import uz.pdp.eufloria.entity.Bucket;
import uz.pdp.eufloria.entity.BucketItem;
import uz.pdp.eufloria.entity.Plant;
import uz.pdp.eufloria.entity.User;
import uz.pdp.eufloria.repository.BucketItemRepository;
import uz.pdp.eufloria.repository.BucketRepository;
import uz.pdp.eufloria.repository.PlantRepository;
import uz.pdp.eufloria.repository.UserRepository;

import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketRepository repository;
    private final BucketItemRepository bucketItemRepository;
    private final PlantRepository plantRepository;
    private final UserRepository userRepository;

    @Transactional
    public BucketResponseDto addToBucket(UUID plantId, int amount) {
        // todo get User from SecurityContextHolder
        Plant plant = plantRepository.findById(plantId).orElseThrow();
        User user = userRepository.findById(UUID.fromString("81b9feb5-ec80-4872-ad74-52c80f622e17")).orElseThrow();

        Bucket bucket = getOrCreateBucket(user);

        BucketItem bucketItem = new BucketItem(plant, bucket, amount);
        bucketItemRepository.save(bucketItem);

        bucket.addItem(plant, amount);
        return repository.save(bucket).toResponseDto();
    }

    @Transactional
    public BucketResponseDto removeFromBucket(UUID plantId) {
        Plant plant = plantRepository.findById(plantId).orElseThrow();
        User user = userRepository.findById(UUID.fromString("81b9feb5-ec80-4872-ad74-52c80f622e17")).orElseThrow();

        Bucket bucket = user.getBucket();
        Optional<BucketItem> optItem = bucketItemRepository.findByBucketAndPlant(bucket, plant);
        if (optItem.isEmpty()) {
            throw ApiException.throwException("Item is not found in the bucket");
        }
        BucketItem item = optItem.get();
        bucket.getItems().remove(item);
        bucketItemRepository.deleteById(item.getBucketId());
        return bucket.toResponseDto();
    }

    private Bucket getOrCreateBucket(User user) {
        return Objects.isNull(user.getBucket()) ? repository.save(new Bucket(user)) : user.getBucket();
    }
}
