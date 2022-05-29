package airport.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Flight {
    @Id
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
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @Override
    public String toString() {
        return "Flight{" +
                "id=" + id +
                ", flightNumber=" + flightNumber +
                ", time=" + time +
                ", flightStatus=" + flightStatus +
                ", active=" + active +
                ", route=" + route +
                '}';
    }
}

