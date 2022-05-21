package airport.repository.impl;

import airport.DAO.PassengerDAO;
import airport.DAO.impl.PassengerDAOImpl;
import airport.entity.Passenger;
import airport.repository.PassengerRepository;

import java.util.List;
import java.util.Optional;

public class PassengerRepositoryImpl implements PassengerRepository {
    private PassengerDAO passengerDAO = new PassengerDAOImpl();

    @Override
    public Passenger add(Passenger passenger) {
        return passengerDAO.add(passenger);
    }

    @Override
    public Optional<Passenger> getById(int id) {
        return passengerDAO.getById(id);
    }

    @Override
    public List<Passenger> getAll() {
        return passengerDAO.getAll();
    }

    @Override
    public List<Passenger> getAllActive() {
        return passengerDAO.getAllActive();
    }

    @Override
    public Passenger update(Passenger passenger) {
        return passengerDAO.update(passenger);
    }

    @Override
    public boolean delete(Passenger passenger) {
        return passengerDAO.delete(passenger);
    }
}
