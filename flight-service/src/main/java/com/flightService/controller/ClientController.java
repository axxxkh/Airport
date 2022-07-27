package com.flightService.controller;

import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import com.flightService.service.PassengerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/passenger")
public class ClientController {

    private PassengerService passengerService;

    @GetMapping("/")
    public List<PassengerDTO> getPassengers(@RequestHeader("Authorization") String jwt) {
        return passengerService.getAll(jwt);
    }
//
//    @GetMapping("/{passengerId}/")
//    public List<TicketDTO> getAllTicketsByPassenger (@RequestHeader("Autorization") String jwt) {
//        passengerService.
//    }

}
