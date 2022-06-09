package airport.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)

public class Flight extends BasicEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "flight_number")
    private int flightNumber;
    private LocalDateTime time;
    @Column(name = "flight_status")
    private byte flightStatus;
    @ManyToOne
    @JoinColumn(name = "airline_id")
    private Airline airline;
    @ManyToOne
    @JoinColumn(name = "craft_id")
    private Aircraft craftId;
    @ManyToOne
    @JoinColumn(name = "gate_id")
    private Gate gate;
    private boolean active = Boolean.TRUE;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
}

