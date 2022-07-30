package com.flightService.repository;


import com.flightService.entity.Airline;

import java.util.Optional;

public interface AirlineRepository extends GenericJPARepository<Airline> {

    Optional<Airline> findByName(String name);
}
