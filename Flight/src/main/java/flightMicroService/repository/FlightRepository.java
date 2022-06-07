package flightMicroService.repository;

import flightMicroService.entity.Airline;
import flightMicroService.entity.Flight;
import flightMicroService.entity.Gate;
import flightMicroService.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findFlightsByAirline(Airline airline);

    List<Flight> findFlightsByGate(Gate gate);

    Flight findByFlightNumber(int flightNumber);

    List<Flight> findByActiveTrue();

    List<Flight> findFlightsByTime(LocalDateTime time);

    List<Flight> findByRoute(Route route);
}
