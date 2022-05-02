package Airport.Logic;

import Airport.Entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class ComplexLogic {

    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static List<Passenger> getPassengersByFlight(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Passenger p " +
                "LEFT JOIN FETCH p.tickets t " +
                "LEFT JOIN FETCH t.flight f " +
                "where f.id=:id");
        query.setParameter("id", flight.getId());

        List<Passenger> passengerList = (List<Passenger>) query.list();
        passengerList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return passengerList;
    }

    public static List<Ticket> getTicketsByFlight(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Ticket t " +
                "LEFT JOIN FETCH t.flight f " +
                "where f.id=:id");
        query.setParameter("id", flight.getId());

        List<Ticket> ticketList = query.list();
        ticketList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return ticketList;
    }

    public static List<Flight> getFlightsByAvialine(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Flight f " +
                "LEFT JOIN FETCH f.avialine a " +
                "where f.id=:id");
        query.setParameter("id", avialine.getId());

        List<Flight> flightList = query.list();
        flightList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return flightList;
    }

    public static List<Flight> getFlightsByGate(Gate gate) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Flight f " +
                "LEFT JOIN FETCH f.avialine a " +
                "where f.id=:id");
        query.setParameter("id", gate.getId());
        List<Flight> flightList = query.list();
        flightList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return flightList;
    }

    public static Ticket buyTicket(Passenger passenger, Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        int purchasedTickets = getTicketsByFlight(flight).size();
        Query query = session.createQuery("from AircraftTypes a " +
                "where a.id=:id");
        query.setParameter("id", flight.getCraftId());

        AircraftTypes aircraftType = (AircraftTypes) query.getSingleResult();

        System.out.println(aircraftType.getCapacity() - purchasedTickets);
        transaction.commit();
        session.close();
        return new Ticket();
    }

    public static List<Flight> getAllFlightsByTerminal(Terminal terminal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from Flight f " +
                "where f.id =:id");
        query.setParameter("id", terminal.getId());
        List<Flight> flightList = query.list();
        transaction.commit();
        session.close();
        return flightList;
    }
}
