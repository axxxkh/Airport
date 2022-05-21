package airport.repository.impl;

import airport.DAO.TicketDAO;
import airport.DAO.impl.TicketDAOImpl;
import airport.entity.Terminal;
import airport.entity.Ticket;
import airport.repository.TerminalRepository;
import airport.repository.TicketRepository;

import java.util.List;
import java.util.Optional;

public class TicketRepositoryImpl implements TicketRepository {
    private TicketDAO ticketDAO = new TicketDAOImpl();

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
}
