package airport.repositoryDAO;

import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Passport;

import java.util.List;
import java.util.Optional;

public interface PassengerRepositoryDAO extends GenericRepositoryDAO<Passenger> {
    List<Passenger> getPassengersByFlight(Flight flight);

    List<Passenger> getByAirline(Airline airline);

    Optional<Passenger> getByPassport(Passport passport);

    Passenger getByIdWithPassports(int id);

    Optional<Passenger> getByIdWithTickets(int id);
}
