package airport.service;

import airport.DTO.FlightDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public interface FlightService {
    byte FLIGHT_STATUS_CREATED = 0;
    byte FLIGHT_STATUS_BOARDING = 1;
    byte FLIGHT_STATUS_FLYING = 2;
    byte FLIGHT_STATUS_FINISHED = 3;
    byte FLIGHT_STATUS_CANCELLED = 4;

    List<FlightDTO> getAllActiveFlights();

    List<FlightDTO> getFlightsByDate(LocalDate date);

    List<FlightDTO> getFlightsByPeriod(LocalDate startDate, LocalDate endDate);

    FlightDTO flightFinished(FlightDTO flightDTO);

    FlightDTO updateDate(FlightDTO flightDTO, LocalDateTime newDate);

    FlightDTO getByNumber(int flightNumber);

//     List<Flight> getFlightsByRoutes(Route route);

}
