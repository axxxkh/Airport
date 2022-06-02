package airport.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDTO {

    private int flightNumber;
    private LocalDateTime time;
    private byte flightStatus;
    private AirlineDTO airline;
    private AircraftDTO craftId;
    private GateDTO gate;
//    private String route;
}
