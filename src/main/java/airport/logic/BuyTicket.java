package airport.logic;

import airport.DAO.impl.TicketDAOImpl;
import airport.DAO.TicketDAO;
import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Ticket;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class BuyTicket {
    public final int TICKET_STATUS_NOT_SOLD = 0;
    public final int TICKET_STATUS_SOLD = 1;
    public final int TICKET_STATUS_BOARDED = 2;
    public final int TICKET_STATUS_FINISHED = 3;

    public Ticket buyTicket(Passenger passenger, Flight flight) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        Queue<Ticket> ticketQueue = new LinkedList<>(getAvailableTickets(flight));
        Ticket ticket = ticketQueue.peek();
        if (ticket != null) {
            ticket.setPassenger(passenger);
            ticketDAO.add(ticket);
            return ticket;
        }
        return null;
    }

    public List<Ticket> getAvailableTickets(Flight flight) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        return ticketDAO.getTicketsByFlight(flight)
                .stream()
                .filter(t -> (t.isActive() && t.getTicketStatus() == TICKET_STATUS_NOT_SOLD))
                .collect(Collectors.toList());
    }
}
