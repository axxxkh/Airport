package airport.repository.impl;

import airport.DAO.FlightDAO;
import airport.DAO.impl.FlightDAOImpl;
import airport.entity.Flight;
import airport.repository.FlightRepository;

import java.util.List;
import java.util.Optional;

public class FlightRepositoryImpl implements FlightRepository {
    private FlightDAO flightDAO = new FlightDAOImpl();

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
}
