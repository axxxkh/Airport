package airport.Repository;

import airport.entity.Airline;

public interface AirlineRepository extends GenericJPARepository<Airline> {

    Airline findByName(String name);
}
