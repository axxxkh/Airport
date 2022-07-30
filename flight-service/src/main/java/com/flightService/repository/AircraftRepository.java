package com.flightService.repository;


import com.flightService.entity.Aircraft;
import com.flightService.entity.Airline;

import java.util.List;

public interface AircraftRepository extends GenericJPARepository<Aircraft> {

    List<Aircraft> getAircraftsByAirline(Airline airline);
}
