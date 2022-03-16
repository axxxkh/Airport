package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import Airport.Entity.Aircraft;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AircraftDAOImpl implements GenericDAO<Aircraft> {
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
    public Aircraft getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Aircraft aircraft = session.get(Aircraft.class, id);
        transaction.commit();
        session.close();
        return aircraft;
    }

    @Override
    public List<Aircraft> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Aircraft> allEntity = session.createQuery("Select a from " + Aircraft.class.getSimpleName()
                + " a", Aircraft.class).getResultList();
        return allEntity;
    }

    @Override
    public Aircraft update(Aircraft aircraft) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(aircraft);
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
}
