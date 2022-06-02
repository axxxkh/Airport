package airport.DAO;

import airport.entity.Aircraft;
import airport.entity.Airline;

import java.util.List;

public interface AircraftDAO extends GenericDAO<Aircraft> {
    List<Aircraft> getAircraftsByAirline(Airline avialine);
}
