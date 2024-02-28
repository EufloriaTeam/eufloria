package uz.pdp.eufloria.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.eufloria.dto.BucketItemResDto;
import uz.pdp.eufloria.dto.BucketResponseDto;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Bucket extends AbsUUIDEntity {
    @OneToOne
    private User user;

    @OneToMany(mappedBy = "bucket")
    private List<BucketItem> items = new ArrayList<>();

    public Bucket(User user) {
        this.id = UUID.randomUUID();
        this.user = user;
    }

    public void addItem(Plant plant, int amount) {
        items.add(new BucketItem(plant, this, amount));
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
