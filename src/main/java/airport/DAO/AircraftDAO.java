package airport.DAO;

import airport.entity.Aircraft;
import airport.entity.Avialine;

import java.util.List;

public interface AircraftDAO extends GenericDAO<Aircraft>{
    List<Aircraft> getAircraftsByAvialine(Avialine avialine);
}
