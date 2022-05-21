package airport.controller;

import airport.entity.Flight;
import airport.service.FlightService;
import airport.service.impl.FlightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class FlightController {

    private FlightService flightService = new FlightServiceImpl();

    @GetMapping
    public String hello() {
         return flightService.getFlightById(1).toString();
    }
}
