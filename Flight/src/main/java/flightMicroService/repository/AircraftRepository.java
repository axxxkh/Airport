package flightMicroService.repository;

import flightMicroService.entity.Aircraft;
import flightMicroService.entity.Airline;

import java.util.List;

public interface AircraftRepository extends GenericJPARepository<Aircraft> {

    List<Aircraft> getAircraftsByAirline(Airline airline);
}
