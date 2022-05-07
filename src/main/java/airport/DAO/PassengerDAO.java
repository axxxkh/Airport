package airport.DAO;

import airport.entity.Avialine;
import airport.entity.Flight;
import airport.entity.Passenger;

import java.util.List;
import java.util.Optional;

public interface PassengerDAO extends GenericDAO<Passenger> {
    List<Passenger> getPassengersByFlight(Flight flight);

    List<Passenger> getByAvialine(Avialine avialine);

    // Optional<Passenger> getByPassport(String passport);
    Passenger getByIdWithPassports(int id);

    Optional<Passenger> getByIdWithTickets(int id);
}
