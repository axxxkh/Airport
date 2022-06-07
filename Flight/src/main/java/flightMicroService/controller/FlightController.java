package flightMicroService.controller;

import flightMicroService.dto.FlightDTO;
import flightMicroService.entity.Flight;
import flightMicroService.service.FlightService;
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

    //    http://localhost:8081/flight/get/Period/?startDate=2021-05-01&endDate=2022-05-05
    @GetMapping("/get/period/")
    public ResponseEntity<List<FlightDTO>> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(flightService.getFlightsByPeriod(startDate, endDate), HttpStatus.ACCEPTED);
    }

//    @GetMapping("/get/date/")
//    public List<FlightDTO> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
//        return flightService.getFlightsByDate(date);
//    }

    @GetMapping("/get/number/")
    public ResponseEntity<FlightDTO> getByPeriod(@RequestParam int flightNumber) {
        return new ResponseEntity<>(flightService.getByNumber(flightNumber),HttpStatus.ACCEPTED);
    }

    @GetMapping("/add/flight/")
    public ResponseEntity<String> addFlight() {

        Flight flight = Flight.builder()
                .flightNumber(100)
                .active(true)
                .build();
        return new ResponseEntity<>("hello", HttpStatus.ACCEPTED);
    }

    @PostMapping("/finished/")
    public ResponseEntity<FlightDTO> flightFinished(FlightDTO flightDTO) {
        return new ResponseEntity<>(flightService.flightFinished(flightDTO), HttpStatus.ACCEPTED);
    }

    @PostMapping("/edit/date/")
    public ResponseEntity<FlightDTO> editDate(@RequestBody FlightDTO flightDTO,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDate) {
        return new ResponseEntity<>(flightService.updateDate(flightDTO, newDate), HttpStatus.ACCEPTED);
    }

    @PostMapping("/flight/flight/")
    public void saveFlight(@RequestBody FlightDTO flight) {

    }
}
