package uz.pdp.eufloria.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@EqualsAndHashCode
@Data
@NoArgsConstructor
public class BucketItem {
    @EmbeddedId
    private BucketId bucketId;

    @MapsId("plantId")
    @ManyToOne
    @JoinColumn(name = "plant_id", referencedColumnName = "id")
    private Plant plant;

    @MapsId("bucketId")
    @ManyToOne
    @JoinColumn(name = "bucket_id", referencedColumnName = "id")
    @ToString.Exclude
    private Bucket bucket;

    @EqualsAndHashCode.Exclude
    private int amount = 1;

    @Embeddable
    @EqualsAndHashCode
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BucketId implements Serializable {
        private UUID plantId;
        private UUID bucketId;
    }

    public BucketItem(Plant plant, Bucket bucket, int amount) {
        this.bucketId = new BucketId(plant.getId(), bucket.getId());
        this.bucket = bucket;
        this.plant = plant;
        this.amount = amount;
    }

    public BucketItem(Plant plant, Bucket bucket) {
        this.bucketId = new BucketId(plant.getId(), bucket.getId());
        this.plant = plant;
        this.bucket = bucket;
    }

    public void addMore(int amount) {
        this.amount += amount;
    }
}
