package airport.service.impl;

import airport.DAO.AircraftDAO;
import airport.DAO.TicketDAO;
import airport.DAO.impl.AircraftDAOImpl;
import airport.DAO.impl.TicketDAOImpl;
import airport.entity.*;
import airport.repository.AircraftRepository;
import airport.repository.TicketRepository;
import airport.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository;
    private AircraftRepository aircraftRepository;

    @Override
    public List<Ticket> generateAndWriteTicketsForFlight(Flight flight) {
        List<Ticket> ticketList;
        AircraftDAO aircraftDAO = new AircraftDAOImpl();
        Aircraft aircraft = flight.getCraftId();
        AircraftType aircraftTypes = aircraft.getTypeId();
        TicketDAO ticketDAO = new TicketDAOImpl();
        ticketList = IntStream.rangeClosed(1, aircraftTypes.getCapacity())
                .mapToObj(seat -> Ticket
                        .builder()
                        .seat(seat)
                        .flight(flight)
                        .ticketStatus(TICKET_STATUS_NOT_SOLD)
                        .build())
                .collect(Collectors.toList());
        ticketList.forEach(System.out::println);
        ticketDAO.addAll(ticketList);
        return ticketList;
    }

    @Override
    public void updateTicketsFlightFinished(Flight flight) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        List<Ticket> ticketList = ticketDAO.getTicketsByFlight(flight);
        ticketList.forEach(t -> t.setTicketStatus(TICKET_STATUS_FINISHED));
        ticketDAO.updateAll(ticketList);
    }

    @Override
    public Ticket buyTicket(Passenger passenger, Flight flight) {
        Queue<Ticket> ticketQueue = new LinkedList<>(getAvailableTickets(flight));
        Ticket ticket = ticketQueue.peek();
        if (ticket != null) {
            ticket.setPassenger(passenger);
            ticketRepository.add(ticket);
            return ticket;
        }
        return null;
    }

    @Override
    public List<Ticket> getAvailableTickets(Flight flight) {

        return ticketRepository.getTicketsByFlight(flight)
                .stream()
                .filter(t -> (t.isActive() && t.getTicketStatus() == TICKET_STATUS_NOT_SOLD))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getAllAvailableTickets() {
        return ticketRepository.getAllActive()
                .stream()
                .filter(t -> (t.isActive() && t.getTicketStatus() == TICKET_STATUS_NOT_SOLD))
                .collect(Collectors.toList());
    }
}
