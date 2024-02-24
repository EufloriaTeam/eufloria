package uz.pdp.eufloria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Plant extends AbsUUIDEntity {
    @Column(nullable = false)
    private String name;

    private String description;

    private double price;

    private double discount;

    @OneToOne
    private Image image;
}
