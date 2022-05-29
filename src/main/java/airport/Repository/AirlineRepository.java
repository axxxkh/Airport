package airport.Repository;

import airport.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AirlineRepository extends JpaRepository<Airline, Integer> {
}
