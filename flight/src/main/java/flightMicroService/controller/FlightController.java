package flightMicroService.controller;

import flightMicroService.dto.FlightDTO;
import flightMicroService.service.FlightService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
@Slf4j
public class FlightController {

    private FlightService flightService;


    @GetMapping("/")
    public ResponseEntity<List<FlightDTO>> getAll() {
        log.info("Get all flights");
//        log.error("error");

        return new ResponseEntity<>(flightService.getAllFlights(), HttpStatus.ACCEPTED);
    }

    @GetMapping("/{number}")
    public ResponseEntity<FlightDTO> getByNumber(@PathVariable int number) {
        return new ResponseEntity<>(flightService.getByNumber(number), HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/{number}")
    public void deleteByNumber(@PathVariable int number) {
        flightService.deleteFlight(number);
    }

    @PostMapping("/")
    public void addFlight(@RequestBody @Valid FlightDTO flight) {
        flightService.addFlight(flight);
    }


    //    http://localhost:8081/flight/Period/?startDate=2021-05-01&endDate=2022-05-05
    @GetMapping("/period/")
    public ResponseEntity<List<FlightDTO>> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        return new ResponseEntity<>(flightService.getFlightsByPeriod(startDate, endDate), HttpStatus.ACCEPTED);
    }

    @GetMapping("/date/")
    public ResponseEntity<List<FlightDTO>> getByDate(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return new ResponseEntity<>(flightService.getByDate(date), HttpStatus.OK);
    }

    @PutMapping("/finished/")
    public ResponseEntity<FlightDTO> flightFinished(@Valid FlightDTO flightDTO) {
        return new ResponseEntity<>(flightService.flightFinished(flightDTO), HttpStatus.ACCEPTED);
    }

    @PutMapping("/date/")
    public ResponseEntity<FlightDTO> editDate(@RequestBody @Valid FlightDTO flightDTO,
                                              @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime newDate) {
        return new ResponseEntity<>(flightService.updateDate(flightDTO, newDate), HttpStatus.ACCEPTED);
    }
}
