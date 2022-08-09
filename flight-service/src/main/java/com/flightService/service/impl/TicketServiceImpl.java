package com.flightService.service.impl;

import com.flightService.dto.TicketDTO;
import com.flightService.entity.Flight;
import com.flightService.entity.Passenger;
import com.flightService.entity.Ticket;
import com.flightService.exceptions.FlightException;
import com.flightService.exceptions.TicketException;
import com.flightService.repository.FlightRepository;
import com.flightService.repository.PassengerRepository;
import com.flightService.repository.TicketRepository;
import com.flightService.service.TicketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
public class TicketServiceImpl implements TicketService {
    private TicketRepository ticketRepository;
    private FlightRepository flightRepository;
    private PassengerRepository passengerRepository;
    private ModelMapper mapper;

    @Override
    public List<TicketDTO> getAvailableTicketsByFlightNumber(Integer flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow();

        List<Ticket> soldAndReservedTickets = ticketRepository.findTicketsByFlightId(flight.getId());
        List<Ticket> allTickets = IntStream.rangeClosed(1, flight.getAircraft().getType().getCapacity())
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
    public TicketDTO buyTicket(TicketDTO ticketDTO) throws TicketException, FlightException {
        if (!getAvailableTicketsByFlightNumber(ticketDTO.getFlightNumber()).contains(ticketDTO)) {
            throw new TicketException("This ticket isn`t available");
        }

        if (getTicketsByPassenger(ticketDTO.getPassengerId()).contains(ticketDTO)) {
            throw new TicketException(String.format("Passenger %s already have this ticket", ticketDTO.getPassengerId()));
        }

        if (getTicketsByPassenger(ticketDTO.getPassengerId())
                .stream()
                .anyMatch(ticket -> ticket.getFlightNumber().equals(ticketDTO.getFlightNumber()))) {
            throw new TicketException(String.format("Passenger %s already have another ticket for this flight", ticketDTO.getPassengerId()));
        }


        Passenger passenger = passengerRepository.getReferenceById(ticketDTO.getPassengerId());
        Flight flight = flightRepository.findByFlightNumber(ticketDTO.getFlightNumber())
                .orElseThrow(() -> new FlightException(String.format("Flight %s doesn`t exist", ticketDTO.getFlightNumber())));

        if (flight.getTime().isBefore(LocalDateTime.now())) {
            throw new FlightException(String.format("Flight %s have been started", ticketDTO.getFlightNumber()));
        }

        Ticket ticket = mapper.map(ticketDTO, Ticket.class);

        ticket.setFlight(flight);
        ticket.setPassenger(passenger);
        // some logic to generate ticket number
        ticket.setId(null);
        ticket.setBuyDate(LocalDate.now());

        List<Ticket> tickets = ticketRepository.findTicketsByPassengerId(ticketDTO.getPassengerId());
        tickets.add(ticket);
        passenger.setTickets(tickets);

        passengerRepository.save(passenger);
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
