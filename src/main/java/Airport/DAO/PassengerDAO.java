package Airport.DAO;

import Airport.Entity.Avialine;
import Airport.Entity.Flight;
import Airport.Entity.Passenger;

import java.util.List;

public interface PassengerDAO extends GenericDAO<Passenger> {
    List<Passenger> getPassengersByFlight(Flight flight);

    List<Passenger> getPassengersByAvialine(Avialine avialine);

}
