package airport.repository;

import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends GenericRepository<Ticket> {
    List<Ticket> getTicketsByFlight(Flight flight);

    List<Ticket> getTicketsByPassenger(Passenger passenger);

    void addAll(List<Ticket> ticketList);

    void updateAll(List<Ticket> ticketList);
}

