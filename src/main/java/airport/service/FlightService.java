package airport.service;

import airport.DTO.FlightDTO;
import airport.entity.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    void flightFinished(FlightDTO flightDTO);

//     List<Flight> getFlightsByRoutes(Route route);

}
