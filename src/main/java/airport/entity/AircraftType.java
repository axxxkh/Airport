package airport.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(name = "Aircraft_type")
public class AircraftType extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String producer;
    private String type;
    private int capacity;
    private boolean active = Boolean.TRUE;
}
