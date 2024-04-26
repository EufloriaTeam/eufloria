package uz.pdp.eufloria.entity;

import jakarta.persistence.*;
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
    @ManyToOne
    private Image image;
}
