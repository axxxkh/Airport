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
    @ManyToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "type_id", referencedColumnName = "id")
    private AircraftTypes typeId;
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    @Column(name = "serial_number")
    private int serialNumber;
    private boolean active;

    @Override
    public String toString() {
        return "Aircraft{" +
                "id=" + id +
                ", airline=" + airline +
                '}';
    }
}
