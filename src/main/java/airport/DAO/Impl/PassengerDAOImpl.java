package airport.DAO.Impl;

import airport.DAO.PassengerDAO;
import airport.entity.Airline;
import airport.entity.Flight;
import airport.entity.Passenger;
import airport.entity.Passport;
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
    public Optional<Passenger> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Passenger> passenger = Optional.ofNullable(session.get(Passenger.class, id));
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public Passenger getByIdWithPassports(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Passenger> passengerQuery = session.createQuery("From Passenger p " +
                "LEFT JOIN FETCH p.passports pas " +
                "WHERE pas.id=:id", Passenger.class);
        passengerQuery.setParameter("id", id);
        Passenger passenger = passengerQuery.getSingleResult();
//        Optional <Passenger> passenger = Optional.ofNullable(session.get(Passenger.class, id));
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
        Passenger passengerFromDB = session.load(Passenger.class, passenger.getId());
        if (passengerFromDB != null) {
            session.delete(passengerFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<Passenger> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Passenger> passengerList = session.createQuery("Select p from "
                + Passenger.class.getSimpleName()
                + " p", Passenger.class).getResultList();
        transaction.commit();
        session.close();
        return passengerList;
    }

    @Override
    public List<Passenger> getAllActive() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Passenger> passengerList = session.createQuery("From "
                + Passenger.class.getSimpleName()
                + " p where p.active=true", Passenger.class).getResultList();
        transaction.commit();
        session.close();
        return passengerList;
    }

    @Override
    public List<Passenger> getPassengersByFlight(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Passenger> query = session.createQuery("from Passenger p " +
                "LEFT JOIN FETCH p.tickets t " +
                "LEFT JOIN FETCH t.flight f " +
                "where f.id=:id", Passenger.class);
        query.setParameter("id", flight.getId());

        List<Passenger> passengerList = query.getResultList();
        passengerList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return passengerList;
    }

    @Override
    public List<Passenger> getByAirline(Airline airline) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Passenger> query = session.createQuery("from Passenger p " +
                "LEFT JOIN FETCH p.tickets t " +
                "LEFT JOIN FETCH t.flight f " +
                "LEFT JOIN FETCH f.airline a " +
                "where a.id=:id", Passenger.class);
        query.setParameter("id", airline.getId());

        List<Passenger> passengerList = query.getResultList();
        transaction.commit();
        session.close();
        return passengerList;
    }

    @Override
    public Optional<Passenger> getByIdWithTickets(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Passenger> passengerQuery = session.createQuery("from Passenger p " +
                "LEFT JOIN FETCH p.tickets " +
                "where p.id=:id", Passenger.class);
        passengerQuery.setParameter("id", id);
        Optional<Passenger> passenger = passengerQuery.uniqueResultOptional();
        transaction.commit();
        session.close();
        return passenger;
    }

    @Override
    public Optional<Passenger> getByPassport(Passport passport) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        PassengerDAO passengerDAO = new PassengerDAOImpl();
        return passengerDAO.getById(passport.getPassenger().getId());
    }
}
