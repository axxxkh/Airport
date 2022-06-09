package airport.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

public class PersonalInfo extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_personal")
    private int idPersonal;
    private boolean married;
    private LocalDate birthday;
    private String city;
    private boolean active = Boolean.TRUE;
}
