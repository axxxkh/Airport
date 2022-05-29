package airport.Repository;

import airport.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

    @Query("from Passenger p LEFT JOIN p.tickets t LEFT JOIN t.flight f where f.flightNumber= ?1")
    List<Passenger> getPassengerByFlightNumber(Integer flightNumber);

    @Query("from Passenger p LEFT JOIN p.tickets t LEFT JOIN t.flight f where f.id= ?1")
    List<Passenger> getPassengerByFlightId(Integer flightId);

    @Query("from Passenger p LEFT JOIN p.tickets t LEFT JOIN t.flight f LEFT JOIN  f.airline a where a.id= ?1")
    List<Passenger> getByAirline(Integer airlineId);

}
