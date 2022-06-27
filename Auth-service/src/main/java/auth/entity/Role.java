package auth.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "roles")
public class Role extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Enumerated(EnumType.STRING)
    private RolesEnum role;
    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @ToString.Exclude
    private Set<Passenger> passengers;
    private boolean active = Boolean.TRUE;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role1 = (Role) o;
        return isActive() == role1.isActive() && Objects.equals(getRole(), role1.getRole());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRole(), isActive());
    }
}
