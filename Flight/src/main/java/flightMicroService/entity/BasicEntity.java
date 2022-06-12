package flightMicroService.entity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class BasicEntity {
    public abstract int getId();
}
