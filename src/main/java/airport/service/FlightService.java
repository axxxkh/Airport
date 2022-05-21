package airport.service;

import airport.entity.Flight;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {

    public Flight addFlight(Flight flight);
    public List<Flight> getAllActiveFlights();
    public Flight getFlightById(int id);

}
