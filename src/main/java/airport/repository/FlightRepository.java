package airport.repository;

import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Gate;
import airport.entity.Route;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends GenericJPARepository<Flight> {

    List<Flight> findFlightsByAirline(Airline airline);

    List<Flight> findFlightsByGate(Gate gate);

    Flight findFlightByFlightNumber(int flightNumber);

    List<Flight> findByActiveTrue();

    List<Flight> findByRoute(Route route);

    List<Flight> findFlightsByTime(LocalDate date);
}
