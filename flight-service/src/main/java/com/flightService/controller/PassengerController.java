package com.flightService.controller;

import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import com.flightService.service.PassengerService;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/passenger")
public class PassengerController {

    private PassengerService passengerService;
    private TicketService ticketService;

    // Return List of PassengerDTOs that attached to user account
    @GetMapping("/")
    public List<PassengerDTO> getPassengers(@RequestHeader("email") String email) {
        return passengerService.getAllByEmail(email);
    }

    @GetMapping("/tickets")
    public List<TicketDTO> getTickets(@RequestHeader("email") String email) {
        return getPassengers(email)
                .stream()
                .map(passengerDTO -> ticketService.getTicketsByPassenger(passengerDTO.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @GetMapping("/{flightId}/test")
    public List<TicketDTO> freeTickets(@PathVariable("flightId") int flightId) {
       return ticketService.getAvailableTicketsByFlightNumber(flightId);
    }
}
