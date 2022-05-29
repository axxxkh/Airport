package airport.Repository;

import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Gate;
import airport.entity.Terminal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> getFlightsByAirline(Airline airline);

    List<Flight> getFlightsByGate(Gate gate);

//    List<Flight> getFlightByTerminal(Terminal terminal);

    Flight getFlightByFlightNumber(int flightNumber);
}
