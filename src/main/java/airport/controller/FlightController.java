package airport.controller;

import airport.DTO.FlightDTO;
import airport.entity.Flight;
import airport.service.FlightEntityService;
import airport.service.PassengerEntityService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class FlightController {

    private FlightEntityService flightService;
    private PassengerEntityService passengerService;
    private ModelMapper modelMapper;

    //    http://localhost:8081/flight/getByPeriod/?startDate=2021-05-01&endDate=2022-05-05
    @GetMapping("/getByPeriod/")
    public List<FlightDTO> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return flightService
                .getFlightsByPeriod(startDate, endDate)
                .stream()
                .map(f -> modelMapper.map(f, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @GetMapping("/addFlight/")
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
