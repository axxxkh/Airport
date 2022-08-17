package com.flightService.service;


import com.flightService.dto.TicketDTO;
import com.flightService.exceptions.FlightException;
import com.flightService.exceptions.TicketException;
import com.flightService.repository.FlightRepository;
import com.flightService.repository.PassengerRepository;
import com.flightService.repository.TicketRepository;
import com.flightService.service.impl.TicketServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class TicketServiceTest {

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TicketService ticketService = new TicketServiceImpl(ticketRepository, flightRepository, passengerRepository, mapper);

    @Test
    public void getAvailableTicketsByFlightNumberTest() throws FlightException {
        List<TicketDTO> ticketDTOS = ticketService.getAvailableTicketsByFlightNumber(11);
        Assertions.assertEquals(347, ticketDTOS.size());
    }

    @Test
    public void getTicketsByPassengerTest() {
        List<TicketDTO> ticketDTOs = ticketService.getTicketsByPassenger(1L);
        Assertions.assertEquals(4, ticketDTOs.size());
    }

    @Test
    public void buyTicketTest_expectTicket() throws TicketException, FlightException {
        TicketDTO ticketDTO = TicketDTO.builder()
                .seat(100)
                .flightNumber(16)
                .passengerId(1L)
                .number(777777)
                .build();
        TicketDTO buyedTicket = ticketService.buyTicket(ticketDTO);
        Assertions.assertEquals(ticketDTO, buyedTicket);
    }

    @Test
    public void buyTicketTest_expectFlightException() throws TicketException, FlightException {
        TicketDTO ticketDTO = TicketDTO.builder()
                .seat(100)
                .flightNumber(166)
                .passengerId(1L)
                .number(777777)
                .build();
        FlightException exception = Assertions.assertThrows(FlightException.class, () -> ticketService.buyTicket(ticketDTO));
        Assertions.assertEquals(String.format("Flight %s doesn't exist", ticketDTO.getFlightNumber()), exception.getMessage());
    }

    @Test
    public void buyTicketTest_expectTicketIsNotAvailable() throws TicketException, FlightException {
        TicketDTO ticketDTO = TicketDTO.builder()
                .seat(1)
                .flightNumber(16)
                .passengerId(1L)
                .number(777777)
                .build();
        TicketException exception = Assertions.assertThrows(TicketException.class, () -> ticketService.buyTicket(ticketDTO));
        Assertions.assertEquals(String.format("Ticket on flight number %s" +
                " with seat %s isn`t available", ticketDTO.getFlightNumber(), ticketDTO.getPassengerId()), exception.getMessage());
    }

    @Test
    public void buyTicketTest_expectAlreadyHaveTicketOnFlight() throws TicketException, FlightException {
        TicketDTO ticketDTO = TicketDTO.builder()
                .seat(32)
                .flightNumber(12)
                .passengerId(2L)
                .number(9999)
                .build();
        TicketException exception = Assertions.assertThrows(TicketException.class, () -> ticketService.buyTicket(ticketDTO));
        Assertions.assertEquals(String.format("Passenger %s already have another ticket for this flight", ticketDTO.getPassengerId()), exception.getMessage());
    }

    @Test
    public void buyTicketTest_expectFlightAlreadyStarted() throws TicketException, FlightException {
        TicketDTO ticketDTO = TicketDTO.builder()
                .seat(32)
                .flightNumber(14)
                .passengerId(1L)
                .number(9999)
                .build();
        FlightException exception = Assertions.assertThrows(FlightException.class, () -> ticketService.buyTicket(ticketDTO));
        Assertions.assertEquals(String.format("Flight %s have been started", ticketDTO.getFlightNumber()), exception.getMessage());
    }
}