package com.flightService.service.impl;

import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import com.flightService.entity.Flight;
import com.flightService.entity.Ticket;
import com.flightService.repository.AircraftRepository;
import com.flightService.repository.FlightRepository;
import com.flightService.repository.TicketRepository;
import com.flightService.service.TicketService;
import com.flightService.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private FlightRepository flightRepository;
    private AircraftRepository aircraftRepository;
    private ModelMapper mapper;
    private JwtUtil jwtUtil;

    @Override
    public List<TicketDTO> getAvailableTicketsByFlightNumber(long flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow();
        List<Ticket> soldAndReservedTickets = ticketRepository.findTicketsByFlightId(flight.getId());
        List<Ticket> allTickets = IntStream.rangeClosed(1, flight.getCraftId().getTypeId().getCapacity())
                .mapToObj(seat -> Ticket.builder()
                        .seat(seat)
                        .flight(flight)
                        .build()
                ).collect(Collectors.toList());

        List<Ticket> availableTicket = allTickets
                .stream()
                .filter(soldAndReservedTickets::contains)
                .collect(Collectors.toList());

        return availableTicket.stream()
                .map(ticket -> mapper.map(ticket,TicketDTO.class))
                .collect(Collectors.toList());
//        List<Ticket> ticketsByCraftType = f
//        List<TicketDTO> freeTickets =

//        return ticketRepository.findTicketsByFlightNumber(flightNumber)
//                .stream()
//                .filter(ticket -> ticket.getTicketStatus() == TICKET_STATUS_NOT_SOLD)
//                .map(ticket -> mapper.map(ticket, TicketDTO.class))
//                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO buyTicket(String passengerId, TicketDTO ticket) {

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
