package airport.service;

import airport.entity.Flight;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface FlightEntityService {
    byte FLIGHT_STATUS_CREATED = 0;
    byte FLIGHT_STATUS_BOARDING = 1;
    byte FLIGHT_STATUS_FLYING = 2;
    byte FLIGHT_STATUS_FINISHED = 3;
    byte FLIGHT_STATUS_CANCELLED = 4;

    List<Flight> getAllActiveFlights();

//    List<Flight> getFlightsByDate(LocalDate date);

    List<Flight> getFlightsByPeriod(LocalDate startDate, LocalDate endDate);

    void flightFinished(Flight flight);

//     List<Flight> getFlightsByRoutes(Route route);

}
