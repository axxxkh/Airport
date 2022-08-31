package com.flightService.service;

import com.flightService.dto.TicketDTO;
import com.flightService.exceptions.FlightException;
import com.flightService.exceptions.TicketException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    byte TICKET_STATUS_NOT_SOLD = 0;
    byte TICKET_STATUS_SOLD = 1;
    byte TICKET_STATUS_BOARDED = 2;
    byte TICKET_STATUS_FINISHED = 3;
    byte TICKET_CANCELLED = 4;

    List<TicketDTO> getAvailableTicketsByFlightNumber(Integer flightNumber) throws FlightException;

    TicketDTO buyTicket(TicketDTO ticket) throws TicketException, FlightException;

    List<TicketDTO> getTicketsByPassenger(long passenger);
}
