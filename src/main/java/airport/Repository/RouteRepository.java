package airport.Repository;

import airport.entity.Airline;
import airport.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends GenericJPARepository<Route> {

    Route findByName(String name);

}
