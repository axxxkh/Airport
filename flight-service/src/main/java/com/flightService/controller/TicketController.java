package com.flightService.controller;

import com.flightService.dto.TicketDTO;
import com.flightService.exceptions.FlightException;
import com.flightService.exceptions.TicketException;
import com.flightService.service.FlightService;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
    private FlightService flightService;
    private TicketService ticketService;

    @GetMapping("/{flightNumber}/")
    public ResponseEntity<List<TicketDTO>> getAllAvailable(@PathVariable("flightNumber") int flightNumber) throws FlightException {
        return ResponseEntity.ok().body(ticketService.getAvailableTicketsByFlightNumber(flightNumber));
    }

    @PostMapping("/")
    public ResponseEntity<TicketDTO> buyTicket(@RequestBody TicketDTO ticket) throws TicketException, FlightException {
        return ResponseEntity.ok().body(ticketService.buyTicket(ticket));
    }
}
