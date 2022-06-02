package airport.Repository;

import airport.entity.Aircraft;
import airport.entity.Airline;

import java.util.List;

public interface AircraftRepository extends GenericJPARepository<Aircraft> {

    List<Aircraft> getAircraftsByAirline(Airline airline);
}
