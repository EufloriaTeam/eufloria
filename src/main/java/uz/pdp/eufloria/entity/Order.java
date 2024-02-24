package uz.pdp.eufloria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "orders")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order extends AbsUUIDEntity {
    @OneToOne
    private Bucket bucket;

    @ManyToOne
    private Shipping shipping;
}
