package airport.controller;

import airport.dto.FlightDTO;
import airport.entity.Flight;
import airport.service.FlightService;
import airport.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class FlightController {

    private FlightService flightService;
    private PassengerService passengerService;

    //    http://localhost:8081/flight/get/Period/?startDate=2021-05-01&endDate=2022-05-05
    @GetMapping("/get/period/")
    public ResponseEntity<List<FlightDTO>> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<List<FlightDTO>>(flightService.getFlightsByPeriod(startDate, endDate), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get/date/")
    public List<FlightDTO> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightService.getFlightsByDate(date);
    }

    @GetMapping("/get/number/")
    public FlightDTO getByPeriod(@RequestParam int flightNumber) {
        return flightService.getByNumber(flightNumber);
    }

    @GetMapping("/add/flight/")
    public ResponseEntity<String> addFlight() {

        Flight flight = Flight.builder()
                .flightNumber(100)
                .active(true)
                .build();
        return new ResponseEntity<String>("hello", HttpStatus.ACCEPTED);
    }

    @PostMapping("/finished/")
    public ResponseEntity<FlightDTO> flightFinished(FlightDTO flightDTO) {
        return new ResponseEntity<FlightDTO>(flightService.flightFinished(flightDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/edit/date/")
    public ResponseEntity<FlightDTO> editDate(@RequestBody FlightDTO flightDTO,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDate) {
        return new ResponseEntity<FlightDTO>(flightService.updateDate(flightDTO, newDate), HttpStatus.ACCEPTED);
    }

    @PostMapping("/flight/flight/")
    public void saveFlight(@RequestBody FlightDTO flight) {

    }
}
