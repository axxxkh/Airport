package Airport.Entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class Flight {
    @Id
    private int id;
    private int flightNumber;
    private LocalDateTime time;
    @Column(name = "flight_status")
    private int flightStatus;
    @Column(name = "terminal_id")
    private int terminalId;
    @ManyToOne
    private Avialine avialine;
    private int craftId;
    private int gateId;
}
