package airport.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TicketDTO {

    private int flightNumber;
    private int number;
    private int seat;
//    private PassengerDTO passengerDTO;
}
