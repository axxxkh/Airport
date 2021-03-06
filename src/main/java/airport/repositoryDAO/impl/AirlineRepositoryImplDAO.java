package airport.repositoryDAO.impl;

import airport.DAO.AirlineDAO;
import airport.DAO.impl.AirlineDAOImpl;
import airport.entity.Airline;
import airport.repositoryDAO.AirlineRepositoryDAO;

import java.util.List;
import java.util.Optional;

public class AirlineRepositoryImplDAO implements AirlineRepositoryDAO {
    private final AirlineDAO airlineDAO = new AirlineDAOImpl();

    @Override
    public Airline add(Airline airline) {
        return airlineDAO.add(airline);
    }

    @Override
    public Optional<Airline> getById(int id) {
        return airlineDAO.getById(id);
    }

    @Override
    public List<Airline> getAll() {
        return airlineDAO.getAll();
    }

    @Override
    public List<Airline> getAllActive() {
        return airlineDAO.getAllActive();
    }

    @Override
    public Airline update(Airline airline) {
        return airlineDAO.update(airline);
    }

    @Override
    public boolean delete(Airline airline) {
        return airlineDAO.delete(airline);
    }
}
