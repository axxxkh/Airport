package com.flightService.service.impl;

import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import com.flightService.repository.TicketRepository;
import com.flightService.service.TicketService;
import com.flightService.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private ModelMapper mapper;
    private JwtUtil jwtUtil;

    @Override
    public List<TicketDTO> getAvailableTicketsByFlightNumber(int flightNumber) {
        return ticketRepository.findTicketsByFlightNumber(flightNumber)
                .stream()
                .filter(ticket -> ticket.getTicketStatus() == TICKET_STATUS_NOT_SOLD)
                .map(ticket -> mapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO buyTicket(String jwt, TicketDTO ticket) {
        Claims claims = jwtUtil.getAllClaimsFromToken(jwt.replace("Bearer ", ""));
//        claims.
        System.out.println(claims);
        return null;
    }

    @Override
    public List<TicketDTO> getTicketsByPassenger(PassengerDTO passenger) {
        return ticketRepository.findTicketsByPassengerId(passenger.getId())
                .stream()
                .map(ticket -> mapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }
}
