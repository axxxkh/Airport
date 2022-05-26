package airport.DTO;

import airport.entity.Gate;
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
    private Gate gateId;
//    private String route;
}
