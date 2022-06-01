package airport.entity;

import javax.persistence.*;

//@Entity
@MappedSuperclass
public abstract class BasicEntity {
    public abstract int getId();
}
