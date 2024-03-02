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
import uz.pdp.eufloria.repository.UserRepository;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BucketService {
    private final BucketRepository repository;
    private final BucketItemRepository bucketItemRepository;
    private final UserRepository userRepository;
    private final PlantRepository plantRepository;

    @Transactional
    public BucketResponseDto toBucket(UUID plantId, int amount) {
        Plant plant = plantRepository.findById(plantId).orElseThrow();
        User user = CommonUtils.getCurrentUser();

        Bucket bucket = getOrCreateBucket(user);
        BucketItem item = new BucketItem(plant, bucket, amount);
        List<BucketItem> items = bucket.getItems();
        int index = items.indexOf(item);
        if (index >= 0) {
            if (items.get(index).getAmount() == amount)
                return bucket.toResponseDto();

            item.setAmount(amount);
            items.set(index, item);
        } else
            items.add(item);

        bucketItemRepository.save(item);
        bucket.setItems(items);
        return repository.save(bucket).toResponseDto();
    }


    @Transactional
    public BucketResponseDto removeFromBucket(UUID plantId) {
        User user = CommonUtils.getCurrentUser();
        Bucket bucket = user.getBucket();
        if (Objects.isNull(bucket)) {
            throw ApiException.throwException("You have no bucket");
        }

        Plant plant = plantRepository.findById(plantId).orElseThrow(() -> ApiException.throwException("Plant not found"));
        int index = bucket.getItems().indexOf(new BucketItem(plant, bucket));
        if (index < 0)
            throw ApiException.throwException("Plant not found in the bucket");
        bucket.getItems().remove(bucket.getItems().get(index));
        bucketItemRepository.deleteById(bucket.getItems().get(index).getBucketId());
        return bucket.toResponseDto();
    }

    private Bucket getOrCreateBucket(User user) {
        if (Objects.isNull(user.getBucket())) {
            user.setBucket(repository.save(new Bucket(user)));
            userRepository.save(user);
            return user.getBucket();
        }
        return user.getBucket();
    }
}
