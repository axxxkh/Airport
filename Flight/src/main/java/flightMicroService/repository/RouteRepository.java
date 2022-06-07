package flightMicroService.repository;

import flightMicroService.entity.Route;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends GenericJPARepository<Route> {

    Route findByName(String name);

}
