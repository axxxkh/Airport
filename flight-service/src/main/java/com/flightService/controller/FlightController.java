package com.flightService.controller;

import com.flightService.entity.Flight;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/flight")
public class FlightController {

    @GetMapping("/")
    public List<Flight> getAllFlights(){
        return null;
    }


}
