package uz.pdp.eufloria.entity.template;


import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.util.UUID;

@MappedSuperclass
@Data
public abstract class AbsUUIDEntity {
    @Id
    @Column(columnDefinition = "uuid default gen_random_uuid()")
    protected UUID id;
}
