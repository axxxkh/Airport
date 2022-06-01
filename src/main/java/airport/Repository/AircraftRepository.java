package airport.Repository;

import airport.entity.Aircraft;
import airport.entity.Airline;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AircraftRepository extends GenericJPARepository<Aircraft> {

    List<Aircraft> getAircraftsByAirline(Airline airline);
}
