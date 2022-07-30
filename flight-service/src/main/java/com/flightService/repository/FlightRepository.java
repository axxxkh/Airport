package com.flightService.repository;

import com.flightService.entity.Airline;
import com.flightService.entity.Flight;
import com.flightService.entity.Gate;
import com.flightService.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FlightRepository extends JpaRepository<Flight, Integer> {

    List<Flight> findFlightsByAirline(Airline airline);

    List<Flight> findFlightsByGate(Gate gate);

    Optional<Flight> findByFlightNumber(int flightNumber);

    List<Flight> findByActiveTrue();

    List<Flight> findFlightsByTime(LocalDateTime time);

    List<Flight> findByRoute(Route route);

    @Query(value = "SELECT * FROM Flight f WHERE f.time>= ?1 and f.time <= ?1 and f.active=true", nativeQuery = true)
    List<Flight> findByDate(LocalDate date);
}
