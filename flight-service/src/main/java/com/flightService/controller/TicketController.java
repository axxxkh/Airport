package com.flightService.controller;

import com.flightService.dto.TicketDTO;
import com.flightService.service.FlightService;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/flight")
@AllArgsConstructor
public class TicketController {
    private FlightService flightService;
    private TicketService ticketService;

    @GetMapping("/{FlightNumber}/")
    public List<TicketDTO> getAllAvailable(@PathVariable("FlightNumber") int flightNumber) {
        return ticketService.getAvailableTicketsByFlightNumber(flightNumber);
    }

    @PostMapping("/{FlightId}/")
    public TicketDTO buyTicket(@RequestHeader("Authorization") String jwt, @RequestBody TicketDTO ticket) {
        ticketService.buyTicket(jwt,ticket);
        return null;
    }
}
