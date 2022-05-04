package Airport.DAO.Impl;

import Airport.DAO.TicketDAO;
import Airport.Entity.Flight;
import Airport.Entity.Passenger;
import Airport.Entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class TicketDAOImpl implements TicketDAO {
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    @Override
    public Ticket add(Ticket ticket) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(ticket);
        transaction.commit();
        session.close();
        return ticket;
    }

    @Override
    public Optional <Ticket> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Ticket> ticket = Optional.ofNullable(session.get(Ticket.class, id));
        transaction.commit();
        session.close();
        return ticket;
    }

    @Override
    public List<Ticket> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Ticket> query = session.createQuery("Select a from "
                + Ticket.class.getSimpleName() + " a", Ticket.class);
        List<Ticket> ticketList = query.getResultList();
        transaction.commit();
        session.close();
        return ticketList;
    }

    @Override
    public Ticket update(Ticket ticket) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(ticket);
        transaction.commit();
        session.close();
        return ticket;
    }

    @Override
    public boolean delete(Ticket ticket) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket persistance = session.load(Ticket.class, ticket.getId());
        if (persistance != null) {
            session.delete(persistance);
            transaction.commit();
            session.close();
            return true;
        }
        session.close();
        return false;
    }

    @Override
    public List<Ticket> getTicketsByFlight(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Ticket> query = session.createQuery("from Ticket t " +
                "LEFT JOIN FETCH t.flight f " +
                "where f.id=:id");
        query.setParameter("id", flight.getId());

        List<Ticket> ticketList = query.getResultList();
        ticketList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return ticketList;
    }

    @Override
    public List<Ticket> getTicketsByPassenger(Passenger passenger) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Ticket> query = session.createQuery("from Ticket t " +
                "LEFT JOIN FETCH t.passenger p " +
                "where p.id=:id");
        query.setParameter("id", passenger.getId());

        List<Ticket> ticketList = query.getResultList();
        ticketList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return ticketList;
    }
}
