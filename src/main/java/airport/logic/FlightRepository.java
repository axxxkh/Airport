package airport.logic;

import airport.DAO.FlightDAO;
import airport.DAO.Impl.FlightDAOImpl;
import airport.DAO.Impl.TicketDAOImpl;
import airport.DAO.TicketDAO;
import airport.entity.Flight;
import airport.entity.Ticket;

import java.util.List;

public class FlightRepository {
    private final byte FLIGHT_STATUS_CREATED = 0;
    private final byte FLIGHT_STATUS_BOARDING = 1;
    private final byte FLIGHT_STATUS_FLYING = 2;
    private final byte FLIGHT_STATUS_FINISHED = 3;
    private final byte FLIGHT_STATUS_CANCELLED = 4;

    public void flightFinished(Flight flight) {
        FlightDAO flightDAO = new FlightDAOImpl();
        TicketDAO ticketDAO = new TicketDAOImpl();
        List<Ticket> ticketList = ticketDAO.getTicketsByFlight(flight);
        flight.setFlightStatus(FLIGHT_STATUS_FINISHED);
        flightDAO.update(flight);
        TicketRepository ticketRepository = new TicketRepository();
        ticketRepository.updateTicketsFlightFinished(flight);

    }
}
