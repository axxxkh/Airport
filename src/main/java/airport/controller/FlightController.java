package airport.controller;

import airport.DTO.FlightDTO;
import airport.entity.Flight;
import airport.service.FlightService;
import airport.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class FlightController {

    private FlightService flightService;
    private PassengerService passengerService;

    //    http://localhost:8081/flight/get/Period/?startDate=2021-05-01&endDate=2022-05-05
    @GetMapping("/get/period/")
    public List<FlightDTO> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return flightService.getFlightsByPeriod(startDate, endDate);
    }

    @GetMapping("/get/date/")
    public List<FlightDTO> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightService.getFlightsByDate(date);
    }

    @GetMapping("/add/flight/")
    public String addFlight() {

        Flight flight = Flight.builder()
                .flightNumber(100)
                .active(true)
                .build();
        return "Success";
    }

    @PostMapping("/flight/flight/")
    public void saveFlight(@RequestBody FlightDTO flight) {

    }
}
