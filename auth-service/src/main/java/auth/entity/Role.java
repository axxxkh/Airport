package auth.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(columnDefinition = "varchar(20) NOT NULL default \"PASSENGER\"")
    private String role;
//    @ManyToMany (fetch = FetchType.LAZY, mappedBy = "roles", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @ToString.Exclude
//    private Set<User> passengers;
//    private boolean active = Boolean.TRUE;
//
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        Role role1 = (Role) o;
//        return isActive() == role1.isActive() && Objects.equals(getRole(), role1.getRole());
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(getRole(), isActive());
//    }
}
