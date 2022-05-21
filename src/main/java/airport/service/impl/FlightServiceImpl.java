package airport.service.impl;

import airport.entity.Flight;
import airport.repository.FlightRepository;
import airport.repository.impl.FlightRepositoryImpl;
import airport.service.FlightService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {
    private FlightRepository flightRepository = new FlightRepositoryImpl();

    @Override
    public Flight addFlight(Flight flight) {
        return null;
    }

    @Override
    public List<Flight> getAllActiveFlights() {
        return flightRepository.getAllActive();
    }

    @Override
    public Flight getFlightById(int id) {
        return flightRepository.getById(id).orElseThrow();
    }
}
