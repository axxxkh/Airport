package flightMicroService.service;

import flightMicroService.dto.FlightDTO;
import flightMicroService.dto.RouteDTO;
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

    List<FlightDTO> getAllFlights();

//    List<FlightDTO> getFlightsByDate(LocalDate date);

    List<FlightDTO> getFlightsByPeriod(LocalDate startDate, LocalDate endDate);

    FlightDTO flightFinished(FlightDTO flightDTO);

    FlightDTO updateDate(FlightDTO flightDTO, LocalDateTime newDateTime);

    FlightDTO getByNumber(int flightNumber);

    List<FlightDTO> getFlightsByRoutes(RouteDTO routeDTO);

    void deleteFlight(FlightDTO flightDTO);
}
