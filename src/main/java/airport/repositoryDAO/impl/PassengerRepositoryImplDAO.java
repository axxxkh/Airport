package airport.repositoryDAO.impl;

import airport.DAO.PassengerDAO;
import airport.DAO.impl.PassengerDAOImpl;
import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Passport;
import airport.repositoryDAO.PassengerRepositoryDAO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PassengerRepositoryImplDAO implements PassengerRepositoryDAO {
    private final PassengerDAO passengerDAO = new PassengerDAOImpl();

    @Override
    public Passenger add(Passenger passenger) {
        return passengerDAO.add(passenger);
    }

    @Override
    public Optional<Passenger> getById(int id) {
        return Optional.ofNullable(passengerDAO.getByIdWithPassports(id));
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

    @Override
    public List<Passenger> getPassengersByFlight(Flight flight) {
        return passengerDAO.getPassengersByFlight(flight);
    }

    @Override
    public List<Passenger> getByAirline(Airline airline) {
        return passengerDAO.getByAirline(airline);
    }

    @Override
    public Optional<Passenger> getByPassport(Passport passport) {
        return passengerDAO.getByPassport(passport);
    }

    @Override
    public Passenger getByIdWithPassports(int id) {
        return passengerDAO.getByIdWithPassports(id);
    }

    @Override
    public Optional<Passenger> getByIdWithTickets(int id) {
        return passengerDAO.getByIdWithTickets(id);
    }
}
