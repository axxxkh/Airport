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
    private int flightStatus;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "terminal_id", referencedColumnName = "id")
    private Terminal terminalId;
    @ManyToOne
    @JoinColumn(name = "avialine_id")
    private Avialine avialine;
    @Column(name = "craft_id")
    private int craftId;
    @Column(name = "gate_id")
    private int gateId;

}

