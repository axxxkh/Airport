package airport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Aircraft {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private AircraftTypes typeId;
    @ManyToOne
    @JoinColumn(name = "avialine_id")
    private Avialine avialine;

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", avialine=" + avialine +
                '}';
    }
}
