package flightMicroService.repository;

import flightMicroService.entity.Airline;

import java.util.Optional;

public interface AirlineRepository extends GenericJPARepository<Airline> {

    Optional<Airline> findByName(String name);
}
