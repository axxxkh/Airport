package com.flightService.controller;

import com.flightService.dto.TicketDTO;
import com.flightService.exceptions.FlightException;
import com.flightService.exceptions.TicketException;
import com.flightService.service.FlightService;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
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

    @GetMapping("/{FlightNumber}/")
    public List<TicketDTO> getAllAvailable(@PathVariable("FlightNumber") int flightNumber) throws FlightException {
        return ticketService.getAvailableTicketsByFlightNumber(flightNumber);
    }

    @GetMapping("/ticketdto")
    public TicketDTO test() {
        return ticketService.getTicketsByPassenger(1).get(0);
    }


    @PostMapping("/")
    public TicketDTO buyTicket(@RequestBody TicketDTO ticket) throws TicketException, FlightException {
        return ticketService.buyTicket(ticket);
    }
}
