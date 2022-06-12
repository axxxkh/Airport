package flightMicroService.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "roles")
public class Role extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String role;
    @ManyToMany (mappedBy = "roles", cascade = CascadeType.ALL)
    @ToString.Exclude
    private Collection<Passenger> passengers;
    private boolean active = Boolean.TRUE;
}
