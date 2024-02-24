package uz.pdp.eufloria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostOffice extends AbsUUIDEntity {
    @Column(nullable = false, unique = true)
    private String postCode;

    @Column(nullable = false)
    private String name;

    @OneToOne
    private Address address;

    @Column(nullable = false)
    private LocalDateTime openTime;

    @Column(nullable = false)
    private LocalDateTime closeTime;
}
