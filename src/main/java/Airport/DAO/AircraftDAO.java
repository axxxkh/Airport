package Airport.DAO;

import Airport.Entity.Aircraft;
import Airport.Entity.Avialine;

import java.util.List;

public interface AircraftDAO extends GenericDAO<Aircraft>{
    List<Aircraft> getAircraftsByAvialine(Avialine avialine);
}
