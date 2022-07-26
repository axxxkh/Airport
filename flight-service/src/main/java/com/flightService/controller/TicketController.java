package com.flightService.controller;

import com.flightService.entity.Ticket;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/flight")
public class TicketController {

    @GetMapping("/{FlightId}/")
    public List<Ticket> getAllAvailable(@PathParam("FlightId") int flightId) {
        return null;
    }

    @PostMapping("/{FlightId}/")
    public Ticket buyTicket(@RequestBody Ticket ticket) {
        return null;
    }
}
