package uz.pdp.eufloria.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.eufloria.dto.bucket.BucketItemResDto;
import uz.pdp.eufloria.dto.bucket.BucketResponseDto;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bucket extends AbsUUIDEntity {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private User user;

    @OneToMany(mappedBy = "bucket", fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private List<BucketItem> items = new ArrayList<>();

    public Bucket(User user) {
        this.id = UUID.randomUUID();
        this.user = user;
    }

    public BucketResponseDto toResponseDto() {
        return new BucketResponseDto(this.items.stream()
                .map(
                        (bItem) -> new BucketItemResDto(
                                bItem.getPlant(), bItem.getAmount()
                        ))
                .collect(Collectors.toList()));
    }
}
