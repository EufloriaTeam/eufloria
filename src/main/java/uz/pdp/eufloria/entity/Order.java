package uz.pdp.eufloria.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.entity.enums.OrderStatus;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbsUUIDEntity {
    @OneToMany
    private List<BucketItem> items;

    @ManyToOne
    private Shipping shipping;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(List<BucketItem> items, Shipping shipping) {
        this.items = items;
        this.shipping = shipping;
    }
}
