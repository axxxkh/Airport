package com.flightService.controller;

import com.flightService.dto.TicketDTO;
import com.flightService.exceptions.TicketException;
import com.flightService.service.FlightService;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticket")
@AllArgsConstructor
public class TicketController {
    private FlightService flightService;
    private TicketService ticketService;

    @GetMapping("/{FlightNumber}/")
    public List<TicketDTO> getAllAvailable(@PathVariable("FlightNumber") long flightNumber) {
        ticketService.getAvailableTicketsByFlightNumber(11).forEach(System.out::println);

//        return ticketService.getAvailableTicketsByFlightNumber(flightNumber);
        return null;
    }

    @GetMapping("/ticketdto")
    public TicketDTO test() {
        return new TicketDTO();
    }


    @PostMapping("/")
    public TicketDTO buyTicket(@RequestBody TicketDTO ticket) throws TicketException {
        ticketService.buyTicket(1, ticket);
        return null;
    }
}
