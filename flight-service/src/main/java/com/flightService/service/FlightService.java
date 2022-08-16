package com.flightService.service;

import com.flightService.dto.FlightDTO;
import com.flightService.exceptions.FlightException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public interface FlightService {
    byte FLIGHT_STATUS_CREATED = 0;
    byte FLIGHT_STATUS_BOARDING = 1;
    byte FLIGHT_STATUS_FLYING = 2;
    byte FLIGHT_STATUS_FINISHED = 3;
    byte FLIGHT_STATUS_CANCELLED = 4;

    List<FlightDTO> getAll();
    List<FlightDTO> getFlightsByPeriod (LocalDate startDate, LocalDate endDate);
    FlightDTO addFlight(FlightDTO flightDTO) throws FlightException;
}
