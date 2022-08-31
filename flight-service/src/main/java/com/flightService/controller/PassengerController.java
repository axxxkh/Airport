package com.flightService.controller;

import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import com.flightService.exceptions.FlightException;
import com.flightService.service.PassengerService;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@Validated
@RequestMapping("/passenger")
public class PassengerController {

    private PassengerService passengerService;
    private TicketService ticketService;

    @GetMapping("/")
    public ResponseEntity<List<PassengerDTO>> getPassengers(@RequestHeader("email") String email) {
        return ResponseEntity.ok().body(passengerService.getAllByEmail(email));
    }

    @GetMapping("/tickets")
    public ResponseEntity<List<TicketDTO>> getTickets(@RequestHeader("email") String email) {
        return ResponseEntity.ok().body(Objects.requireNonNull(getPassengers(email).getBody())
                .stream()
                .map(passengerDTO -> ticketService.getTicketsByPassenger(passengerDTO.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }
}
