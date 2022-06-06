package airport.service;

import airport.dto.FlightDTO;
import airport.dto.PassengerDTO;
import airport.dto.TicketDTO;
import airport.entity.Flight;
import airport.entity.Ticket;

import java.time.LocalDate;
import java.util.List;

public interface TicketService {
    byte TICKET_STATUS_NOT_SOLD = 0;
    byte TICKET_STATUS_SOLD = 1;
    byte TICKET_STATUS_BOARDED = 2;
    byte TICKET_STATUS_FINISHED = 3;
    byte TICKET_CANCELLED = 4;

    List<Ticket> generateAndWriteTicketsForFlight(Flight flight);

    void updateTicketsFlightFinished(Flight flight);

    TicketDTO buyTicket(PassengerDTO passengerDTO, int flightNumber);

    List<TicketDTO> getAvailableTickets(FlightDTO flight);

    List<TicketDTO> getAllAvailableTickets();

    List<TicketDTO> getAllBuyedTicketsByPeriod(LocalDate startDate, LocalDate endDate);

    void updateTicketsFlightCancelled(Iterable<Ticket> tickets);

}
