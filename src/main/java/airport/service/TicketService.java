package airport.service;

import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Ticket;

import java.util.List;

public interface TicketService {
    byte TICKET_STATUS_NOT_SOLD = 0;
    byte TICKET_STATUS_SOLD = 1;
    byte TICKET_STATUS_BOARDED = 2;
    byte TICKET_STATUS_FINISHED = 3;

    List<Ticket> generateAndWriteTicketsForFlight(Flight flight);

    void updateTicketsFlightFinished(Flight flight);

    Ticket buyTicket(Passenger passenger, Flight flight);

    List<Ticket> getAvailableTickets(Flight flight);

    List<Ticket> getAllAvailableTickets();
}
