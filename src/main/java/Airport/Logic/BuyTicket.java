package Airport.Logic;

import Airport.DAO.FlightDAO;
import Airport.DAO.Impl.FlightDAOImpl;
import Airport.DAO.Impl.PassengerDAOImpl;
import Airport.DAO.Impl.TicketDAOImpl;
import Airport.DAO.PassengerDAO;
import Airport.DAO.TicketDAO;
import Airport.Entity.Flight;
import Airport.Entity.Passenger;
import Airport.Entity.Ticket;

public class BuyTicket {
    public static Ticket buyTicket(Passenger passenger, Flight flight) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        PassengerDAO passengerDAO = new PassengerDAOImpl();
        FlightDAO flightDAO = new FlightDAOImpl();

       if  (!passengerDAO.getById(passenger.getId()).orElseThrow().getTickets().contains(passenger)) {

       }

        return new Ticket();
    }
}
