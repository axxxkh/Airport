package airport.repository.impl;

import airport.DAO.FlightDAO;
import airport.DAO.impl.FlightDAOImpl;
import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Gate;
import airport.entity.Terminal;
import airport.repository.FlightRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class FlightRepositoryImpl implements FlightRepository {
    private final FlightDAO flightDAO = new FlightDAOImpl();

    @Override
    public Flight add(Flight flight) {
        return flightDAO.add(flight);
    }

    @Override
    public Optional<Flight> getById(int id) {
        return flightDAO.getById(id);
    }

    @Override
    public List<Flight> getAll() {
        return flightDAO.getAll();
    }

    @Override
    public List<Flight> getAllActive() {
        return flightDAO.getAllActive();
    }

    @Override
    public Flight update(Flight flight) {
        return flightDAO.update(flight);
    }

    @Override
    public boolean delete(Flight flight) {
        return flightDAO.delete(flight);
    }

    @Override
    public List<Flight> getFlightsByAirline(Airline avialine) {
        return flightDAO.getFlightsByAirline(avialine);
    }

    @Override
    public List<Flight> getFlightsByGate(Gate gate) {
        return flightDAO.getFlightsByGate(gate);
    }

    @Override
    public List<Flight> getFlightByTerminal(Terminal terminal) {
        return flightDAO.getFlightByTerminal(terminal);
    }

    @Override
    public Flight getFlightByNumber(int flightNumber) {
        return flightDAO.getFlightByNumber(flightNumber);
    }
}
