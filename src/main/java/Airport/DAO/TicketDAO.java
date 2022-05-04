package Airport.DAO;

import Airport.Entity.Flight;
import Airport.Entity.Passenger;
import Airport.Entity.Ticket;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket>{
    List<Ticket> getTicketsByFlight(Flight flight);
    List<Ticket> getTicketsByPassenger(Passenger passenger);

}
