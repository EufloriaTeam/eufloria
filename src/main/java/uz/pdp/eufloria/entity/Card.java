package uz.pdp.eufloria.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.eufloria.entity.enums.CardType;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Card extends AbsUUIDEntity {
    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    private CardType type;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false, unique = true)
    private String number;

    private double balance;

    private String cvc;
}
