package Airport.DAO.Impl;

import Airport.DAO.PassengerDAO;
import Airport.Entity.Avialine;
import Airport.Entity.Flight;
import Airport.Entity.Passenger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PassengerDAOImpl implements PassengerDAO {
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
    public Passenger add(Passenger passenger) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passenger);
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public Optional <Passenger> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Passenger> passenger = Optional.ofNullable(session.get(Passenger.class, id));
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public Passenger update(Passenger passenger) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(passenger);
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public boolean delete(Passenger passenger) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Passenger persistance = session.load(Passenger.class, passenger.getId());
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
    public List<Passenger> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Passenger> passengerList = session.createQuery("Select a from "
                + Passenger.class.getSimpleName()
                + " a", Passenger.class).getResultList();
        session.close();
        return passengerList;
    }

    @Override
    public List<Passenger> getPassengersByFlight(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Passenger> query = session.createQuery("from Passenger p " +
                "LEFT JOIN FETCH p.tickets t " +
                "LEFT JOIN FETCH t.flight f " +
                "where f.id=:id");
        query.setParameter("id", flight.getId());

        List<Passenger> passengerList = (List<Passenger>) query.getResultList();
        passengerList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return passengerList;
    }

    @Override
    public List<Passenger> getPassengersByAvialine(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Passenger> query = session.createQuery("from Passenger p " +
                "LEFT JOIN FETCH p.tickets t " +
                "LEFT JOIN FETCH t.flight f " +
                "LEFT JOIN FETCH f.avialine a " +
                "where a.id=:id");
        query.setParameter("id", avialine.getId());

        List<Passenger> passengerList = (List<Passenger>) query.getResultList();
        transaction.commit();
        session.close();
        return passengerList;
    }
}
