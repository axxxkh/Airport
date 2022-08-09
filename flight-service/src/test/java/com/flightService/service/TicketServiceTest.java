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
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class TicketServiceTest {
    @Autowired
    private MockMvc mockMvc;

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


    @BeforeTestMethod(value = "buyTicketTest_expectTicket")
    void initUseCase() {
        System.out.println("before method");
    }

    @Test
    public void getAvailableTicketsByFlightNumberTest() {
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
}
