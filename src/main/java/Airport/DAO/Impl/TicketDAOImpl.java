package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import Airport.Entity.Ticket;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class TicketDAOImpl implements GenericDAO<Ticket> {
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
    public Ticket getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Ticket ticket = session.get(Ticket.class, id);
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
        session.saveOrUpdate(ticket);
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
}
