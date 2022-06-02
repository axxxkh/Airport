package airport.repositoryDAO;

import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Gate;
import airport.entity.Terminal;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepositoryDAO extends GenericRepositoryDAO<Flight> {
    List<Flight> getFlightsByAirline(Airline avialine);

    List<Flight> getFlightsByGate(Gate gate);

    List<Flight> getFlightByTerminal(Terminal terminal);

    Flight getFlightByNumber(int flightNumber);
}
