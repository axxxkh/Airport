package airport.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Builder
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor

public class Salary extends BasicEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "id_personal")
    @JoinColumn(name = "id_personal", referencedColumnName = "id")
    private int idPersonal;
    private String position;
    private int salary;
    private boolean active = Boolean.TRUE;
}
