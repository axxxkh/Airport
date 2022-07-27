package com.flightService.sarvice;

import com.flightService.entity.Flight;
import com.flightService.repository.FlightRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class FlightService {
    private FlightRepository flightRepository;

    public List<Flight> getAll () {
        return flightRepository.findAll();
    }

}
