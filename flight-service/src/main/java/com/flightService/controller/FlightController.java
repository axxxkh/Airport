package com.flightService.controller;

import com.flightService.dto.FlightDTO;
import com.flightService.entity.Flight;
import com.flightService.service.impl.FlightServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/flight")
public class FlightController {

    private FlightServiceImpl flightService;

    @GetMapping("/")
    public List<FlightDTO> getAllFlights(@RequestHeader("Authorization")String token){
        return null;
    }

//    @GetMapping("/period/")
//    public ResponseEntity<List<FlightDTO>> getByPeriod(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
//                                                       @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
//        return new ResponseEntity<>(flightService.getFlightsByPeriod(startDate, endDate), HttpStatus.ACCEPTED);
//    }


}
