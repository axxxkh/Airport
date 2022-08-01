package com.flightService.controller;

import com.flightService.dto.FlightDTO;
import com.flightService.service.impl.FlightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/flight")
public class FlightController {

    private FlightServiceImpl flightService;

    @GetMapping("/")
    public List<FlightDTO> getAllFlights(@RequestHeader("email") String id) {
        return flightService.getAll();
    }

//    @GetMapping("/period/")
//    public ResponseEntity<List<FlightDTO>> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        return new ResponseEntity<>(flightService.getFlightsByPeriod(startDate, endDate), HttpStatus.ACCEPTED);
//    }


}
