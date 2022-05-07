package airport.DAO;

import airport.entity.Avialine;
import airport.entity.Flight;
import airport.entity.Gate;
import airport.entity.Terminal;

import java.util.List;

public interface FlightDAO extends GenericDAO<Flight>{
    List<Flight> getFlightsByAvialine(Avialine avialine);

    List<Flight> getFlightsByGate(Gate gate);

    List<Flight> getFlightByTerminal(Terminal terminal);
}
