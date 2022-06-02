package airport.repositoryDAO;

import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Ticket;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepositoryDAO extends GenericRepositoryDAO<Ticket> {
    List<Ticket> getTicketsByFlight(Flight flight);

    List<Ticket> getTicketsByPassenger(Passenger passenger);

    void addAll(List<Ticket> ticketList);

    void updateAll(List<Ticket> ticketList);
}

