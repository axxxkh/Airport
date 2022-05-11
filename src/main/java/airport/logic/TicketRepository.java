package airport.logic;

import airport.DAO.AircraftDAO;
import airport.DAO.Impl.AircraftDAOImpl;
import airport.DAO.Impl.TicketDAOImpl;
import airport.DAO.TicketDAO;
import airport.entity.Aircraft;
import airport.entity.AircraftType;
import airport.entity.Flight;
import airport.entity.Ticket;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TicketRepository {
    private static final byte TICKET_STATUS_CREATED = 0;
    private static final byte TICKET_STATUS_SOLD = 1;
    private static final byte TICKET_STATUS_BOARDED = 2;
    private static final byte TICKET_STATUS_FINISHED = 3;

    public List<Ticket> generateAndWriteTicketsForFlight(Flight flight) {
        List<Ticket> ticketList;
        AircraftDAO aircraftDAO = new AircraftDAOImpl();
        Aircraft aircraft = aircraftDAO.getById(flight.getCraftId()).orElseThrow();
        AircraftType aircraftTypes = aircraft.getTypeId();
        TicketDAO ticketDAO = new TicketDAOImpl();
        ticketList = IntStream.rangeClosed(1, aircraftTypes.getCapacity())
                .mapToObj(seat -> Ticket
                        .builder()
                        .seat(seat)
                        .flight(flight)
                        .ticketStatus(TICKET_STATUS_CREATED)
                        .build())
                .collect(Collectors.toList());
        ticketList.forEach(System.out::println);
        ticketDAO.addAll(ticketList);
        return ticketList;
    }

    public void updateTicketsFlightFinished(Flight flight) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        List<Ticket> ticketList = ticketDAO.getTicketsByFlight(flight);
        ticketList.forEach(t->t.setTicketStatus(TICKET_STATUS_FINISHED));
        ticketDAO.updateAll(ticketList);
    }
}
