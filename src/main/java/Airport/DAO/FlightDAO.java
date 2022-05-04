package Airport.DAO;

import Airport.Entity.Avialine;
import Airport.Entity.Flight;
import Airport.Entity.Gate;
import Airport.Entity.Terminal;

import java.util.List;

public interface FlightDAO extends GenericDAO<Flight>{
    List<Flight> getFlightsByAvialine(Avialine avialine);

    List<Flight> getFlightsByGate(Gate gate);

    List<Flight> getFlightByTerminal(Terminal terminal);
}
