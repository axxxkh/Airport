package airport.Repository;

import airport.entity.Aircraft;
import airport.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface AircraftRepository extends JpaRepository<Aircraft, Integer> {

    List<Aircraft> getAircraftsByAirline(@Param("id") Airline airline);
}
