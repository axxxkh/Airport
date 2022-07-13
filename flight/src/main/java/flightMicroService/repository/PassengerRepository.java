package flightMicroService.repository;

import flightMicroService.entity.Passenger;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PassengerRepository extends GenericJPARepository<Passenger> {

    @Query("from Passenger p LEFT JOIN p.tickets t LEFT JOIN t.flight f where f.flightNumber= ?1")
    List<Passenger> getPassengerByFlightNumber(Integer flightNumber);

    @Query("from Passenger p LEFT JOIN p.tickets t LEFT JOIN t.flight f where f.id= ?1")
    List<Passenger> getPassengerByFlightId(Integer flightId);

    @Query("from Passenger p LEFT JOIN p.tickets t LEFT JOIN t.flight f LEFT JOIN  f.airline a where a.id= ?1")
    List<Passenger> getByAirline(Integer airlineId);

    Optional<Passenger> findByUsername(String username);

}
