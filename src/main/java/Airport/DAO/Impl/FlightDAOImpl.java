package Airport.DAO.Impl;

import Airport.DAO.FlightDAO;
import Airport.Entity.Avialine;
import Airport.Entity.Flight;
import Airport.Entity.Gate;
import Airport.Entity.Terminal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class FlightDAOImpl implements FlightDAO {
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
    public Optional <Flight> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Flight> flight = Optional.ofNullable(session.get(Flight.class, id));
        transaction.commit();
        session.close();
        return flight;
    }

    @Override
    public Flight update(Flight flight) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(flight);
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

    @Override
    public List<Flight> getFlightsByAvialine(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Flight> query = session.createQuery("from Flight f " +
                "LEFT JOIN FETCH f.avialine a " +
                "where f.id=:id");
        query.setParameter("id", avialine.getId());

        List<Flight> flightList = query.getResultList();
        flightList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return flightList;
    }

    @Override
    public List<Flight> getFlightsByGate(Gate gate) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Flight> query = session.createQuery("from Flight f " +
                "LEFT JOIN FETCH f.avialine a " +
                "where f.id=:id");
        query.setParameter("id", gate.getId());
        List<Flight> flightList = query.getResultList();
        flightList.forEach(System.out::println);
        transaction.commit();
        session.close();
        return flightList;
    }

    @Override
    public List<Flight> getFlightByTerminal(Terminal terminal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Flight> query = session.createQuery("from Flight f " +
                "where f.id =:id");
        query.setParameter("id", terminal.getId());
        List<Flight> flightList = query.getResultList();
        transaction.commit();
        session.close();
        return flightList;
    }
}
