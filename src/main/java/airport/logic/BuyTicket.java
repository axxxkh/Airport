package airport.logic;

import airport.DAO.FlightDAO;
import airport.DAO.Impl.FlightDAOImpl;
import airport.DAO.Impl.PassengerDAOImpl;
import airport.DAO.Impl.TicketDAOImpl;
import airport.DAO.PassengerDAO;
import airport.DAO.TicketDAO;
import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Ticket;

import java.util.List;

public class BuyTicket {
    public static Ticket buyTicket(Passenger passenger, Flight flight) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        PassengerDAO passengerDAO = new PassengerDAOImpl();
        FlightDAO flightDAO = new FlightDAOImpl();

//       if  (!passengerDAO.getById(passenger.getId()).orElseThrow().getTickets().contains(passenger)) {
//       }

        Ticket ticket = Ticket.builder()
                .flight(flight)
                .passenger(passenger)
                .number(11)
                .seat(15)
                .build();
        List<Ticket> ticketList = new TicketDAOImpl().getTicketsByPassenger(passenger);
        ticketDAO.add(ticket);
        passenger.setTickets(ticketList);
        passengerDAO.update(passenger);
        return  ticket;
    }
}
