package airport.repository;

import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Gate;
import airport.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findFlightsByAirline(Airline airline);

    List<Flight> findFlightsByGate(Gate gate);

    Flight findByFlightNumber(int flightNumber);

    List<Flight> findByActiveTrue();

    List<Flight> findFlightsByDate(LocalDate date);

    List<Flight> findByRoute(Route route);
}
