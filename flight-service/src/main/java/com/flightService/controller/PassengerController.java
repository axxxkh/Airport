package com.flightService.controller;

import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import com.flightService.service.PassengerService;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<PassengerDTO> getPassengers(@RequestHeader("id") String id, @RequestHeader("Role") String role, @RequestHeader("Authorization") String jwt) {
        System.out.println(id);
        System.out.println(role);
        return passengerService.getAll(jwt);
    }

//    @GetMapping("/tickets")
//    public List<TicketDTO> getTickets(@RequestHeader("Authorization") String jwt) {
//        return getPassengers(jwt)
//                .stream()
//                .map(passengerDTO -> ticketService.getTicketsByPassenger(passengerDTO))
//                .flatMap(Collection::stream)
//                .collect(Collectors.toList());
//    }


//
//    @GetMapping("/{passengerId}/")
//    public List<TicketDTO> getAllTicketsByPassenger (@RequestHeader("Autorization") String jwt) {
//        passengerService.
//    }
}
