package uz.pdp.eufloria.entity;

import jakarta.persistence.*;
import lombok.*;
import uz.pdp.eufloria.entity.template.AbsUUIDEntity;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User extends AbsUUIDEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Plant> favouritePlants;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Card> cards;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Address> addresses;

    @OneToMany
    private List<Order> orders;

    @OneToOne(fetch = FetchType.EAGER)
    @ToString.Exclude
    private Bucket bucket;

    // may change to enum
    @ManyToOne(fetch = FetchType.EAGER)
    private Role role;
}
