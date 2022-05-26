package airport.repository.impl;

import airport.DAO.TicketDAO;
import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Ticket;
import airport.repository.TicketRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@AllArgsConstructor
public class TicketRepositoryImpl implements TicketRepository {
    private TicketDAO ticketDAO;

    @Override
    public Ticket add(Ticket ticket) {
        return ticketDAO.add(ticket);
    }

    @Override
    public Optional<Ticket> getById(int id) {
        return ticketDAO.getById(id);
    }

    @Override
    public List<Ticket> getAll() {
        return ticketDAO.getAll();
    }

    @Override
    public List<Ticket> getAllActive() {
        return ticketDAO.getAllActive();
    }

    @Override
    public Ticket update(Ticket ticket) {
        return ticketDAO.update(ticket);
    }

    @Override
    public boolean delete(Ticket ticket) {
        return ticketDAO.delete(ticket);
    }

    @Override
    public List<Ticket> getTicketsByFlight(Flight flight) {
        return ticketDAO.getTicketsByFlight(flight);
    }

    @Override
    public List<Ticket> getTicketsByPassenger(Passenger passenger) {
        return ticketDAO.getTicketsByPassenger(passenger);
    }

    @Override
    public void addAll(List<Ticket> ticketList) {
        ticketDAO.addAll(ticketList);
    }

    @Override
    public void updateAll(List<Ticket> ticketList) {
        ticketDAO.updateAll(ticketList);
    }
}
