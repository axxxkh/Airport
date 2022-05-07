package airport.DAO;

import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Ticket;

import java.util.List;

public interface TicketDAO extends GenericDAO<Ticket>{
    List<Ticket> getTicketsByFlight(Flight flight);
    List<Ticket> getTicketsByPassenger(Passenger passenger);

}
