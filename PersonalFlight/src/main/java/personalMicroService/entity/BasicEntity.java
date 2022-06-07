package personalMicroService.entity;

import javax.persistence.MappedSuperclass;

//@Entity
@MappedSuperclass
public abstract class BasicEntity {
    public abstract int getId();
}
