package personalMicroService.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
    //    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "id_personal")
    private int idPersonal;
    private boolean married;
    private LocalDate birthday;
    private String city;
    private boolean active = Boolean.TRUE;
}
