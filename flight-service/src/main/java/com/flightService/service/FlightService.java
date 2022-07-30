package com.flightService.service;

import com.flightService.dto.FlightDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FlightService {
    byte FLIGHT_STATUS_CREATED = 0;
    byte FLIGHT_STATUS_BOARDING = 1;
    byte FLIGHT_STATUS_FLYING = 2;
    byte FLIGHT_STATUS_FINISHED = 3;
    byte FLIGHT_STATUS_CANCELLED = 4;

    public List<FlightDTO> getAll();
}
