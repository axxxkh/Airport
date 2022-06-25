package flightMicroService.repository;

import flightMicroService.entity.Airline;

public interface AirlineRepository extends GenericJPARepository<Airline> {

    Airline findByName(String name);
}
