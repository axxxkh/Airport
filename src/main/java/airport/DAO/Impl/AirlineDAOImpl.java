package airport.DAO.Impl;

import airport.DAO.AirlineDAO;
import airport.entity.Airline;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class AirlineDAOImpl implements AirlineDAO {
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
    public Airline add(Airline avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(avialine);
        transaction.commit();
        session.close();
        return avialine;
    }

    @Override
    public Optional <Airline> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Airline> avialine = Optional.ofNullable(session.get(Airline.class, id));
        transaction.commit();
        session.close();
        return avialine;
    }

    @Override
    public Airline update(Airline avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(avialine);
        transaction.commit();
        session.close();
        return avialine;
    }

    @Override
    public boolean delete(Airline airline) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Airline airlineFromDB = session.load(Airline.class, airline.getId());
        if (airlineFromDB != null) {
            session.delete(airlineFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<Airline> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Airline> airlineList = session.createQuery("Select a from " + Airline.class.getSimpleName()
                + " a", Airline.class).getResultList();
        transaction.commit();
        session.close();
        return airlineList;
    }
}
