package uz.pdp.eufloria.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

@Entity
public class Payment extends AbsUUIDEntity {
    @OneToOne
    private Order order;

    private boolean isPaid;

    @ManyToOne
    private Card card;
}
