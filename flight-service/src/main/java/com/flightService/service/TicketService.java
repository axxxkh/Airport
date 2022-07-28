package com.flightService.service;

import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TicketService {
    byte TICKET_STATUS_NOT_SOLD = 0;
    byte TICKET_STATUS_SOLD = 1;
    byte TICKET_STATUS_BOARDED = 2;
    byte TICKET_STATUS_FINISHED = 3;
    byte TICKET_CANCELLED = 4;

    public List<TicketDTO> getAvailableTicketsByFlightNumber(int flightNumber);
    public TicketDTO buyTicket(String jwt, TicketDTO ticket);
    public List<TicketDTO> getTicketsByPassenger(PassengerDTO passenger);
}
