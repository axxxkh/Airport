package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import Airport.Entity.Flight;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class FlightDAOImpl implements GenericDAO<Flight> {
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
    public Flight add(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(flight);
        transaction.commit();
        session.close();
        return flight;
    }

    @Override
    public Flight getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Flight flight = session.get(Flight.class, id);
        transaction.commit();
        session.close();
        return flight;
    }

    @Override
    public Flight update(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(flight);
        transaction.commit();
        session.close();
        return flight;
    }

    @Override
    public boolean delete(Flight flight) {
        return false;
    }

    @Override
    public List<Flight> getAll() {
        Session session = getSessionFactory().openSession();

        List<Flight> flightList = session.createQuery("Select a from " + Flight.class.getSimpleName()
                + " a", Flight.class).getResultList();

        session.close();
        return flightList;
    }
}
