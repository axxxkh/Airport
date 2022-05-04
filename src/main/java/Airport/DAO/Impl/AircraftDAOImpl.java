package Airport.DAO.Impl;

import Airport.DAO.AircraftDAO;
import Airport.Entity.Aircraft;
import Airport.Entity.Avialine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class AircraftDAOImpl implements AircraftDAO {
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
    public Aircraft add(Aircraft aircraft) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(aircraft);
        transaction.commit();
        session.close();
        return aircraft;
    }

    @Override
    public Optional<Aircraft> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Aircraft> aircraft = Optional.ofNullable(session.get(Aircraft.class, id));
        transaction.commit();
        session.close();
        return aircraft;
    }

    @Override
    public Aircraft update(Aircraft aircraft) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(aircraft);
        transaction.commit();
        session.close();
        return aircraft;
    }

    @Override
    public boolean delete(Aircraft aircraft) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Aircraft persistance = session.load(Aircraft.class, aircraft.getId());
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
    public List<Aircraft> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Aircraft> aircraftList = session.createQuery("Select a from "
                + Aircraft.class.getSimpleName()
                + " a", Aircraft.class).getResultList();
        transaction.commit();
        session.close();
        return aircraftList;
    }

    @Override
    public List<Aircraft> getAircraftsByAvialine(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Aircraft> query = session.createQuery("from Aircraft a " +
                "where a.avialine=:id");
        query.setParameter("id", avialine.getId());
        List<Aircraft> aircraftList = query.getResultList();
        transaction.commit();
        session.close();
        return aircraftList;
    }
}
