package flightMicroService.repository;

import flightMicroService.entity.Route;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RouteRepository extends GenericJPARepository<Route> {

    Optional<Route> findByName(String name);

}
