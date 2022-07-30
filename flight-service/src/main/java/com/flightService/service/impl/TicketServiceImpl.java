package com.flightService.service.impl;

import com.flightService.dto.TicketDTO;
import com.flightService.entity.Flight;
import com.flightService.entity.Passenger;
import com.flightService.entity.Ticket;
import com.flightService.exceptions.TicketException;
import com.flightService.repository.AircraftRepository;
import com.flightService.repository.FlightRepository;
import com.flightService.repository.PassengerRepository;
import com.flightService.repository.TicketRepository;
import com.flightService.service.TicketService;
import com.flightService.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private FlightRepository flightRepository;
    private AircraftRepository aircraftRepository;
    private PassengerRepository passengerRepository;
    private ModelMapper mapper;
    private JwtUtil jwtUtil;

    @Override
    public List<TicketDTO> getAvailableTicketsByFlightNumber(int flightNumber) {
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
                .filter(ticket -> !soldAndReservedTickets.contains(ticket))
                .collect(Collectors.toList());

        return availableTicket.stream()
                .map(ticket -> mapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TicketDTO buyTicket(long passengerId, TicketDTO ticketDTO) throws TicketException {
        if (getAvailableTicketsByFlightNumber(ticketDTO.getFlightNumber()).contains(ticketDTO)) {
            throw new TicketException("This ticket isn`t available");
        }

        if (getTicketsByPassenger(passengerId).contains(ticketDTO)) {
            throw new TicketException(String.format("Passenger %s already have this ticket", passengerId));
        }

        if (getTicketsByPassenger(passengerId)
                .stream()
                .anyMatch(ticket -> ticket.getFlightNumber() == ticketDTO.getFlightNumber())) {
            throw new TicketException(String.format("Passenger %s already have another ticket for this flight", passengerId));
        }


        Passenger passenger = passengerRepository.getReferenceById(passengerId);
        Flight flight = flightRepository.findByFlightNumber(ticketDTO.getFlightNumber()).orElseThrow();
        Ticket ticket = mapper.map(ticketDTO, Ticket.class);

        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        // some logic to generate ticket number
        ticket.setBuyDate(LocalDate.now());

        List<Ticket> tickets = passenger.getTickets();
        tickets.add(ticket);
        passenger.setTickets(tickets);

        passengerRepository.save(passenger);
        ticketRepository.save(ticket);
        return mapper.map(ticket, TicketDTO.class);
    }

    @Override
    public List<TicketDTO> getTicketsByPassenger(long passengerId) {
        return ticketRepository.findTicketsByPassengerId(passengerId)
                .stream()
                .map(ticket -> mapper.map(ticket, TicketDTO.class))
                .collect(Collectors.toList());
    }
}
