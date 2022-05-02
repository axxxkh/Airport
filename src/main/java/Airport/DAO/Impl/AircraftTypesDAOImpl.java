package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import Airport.Entity.AircraftTypes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;


public class AircraftTypesDAOImpl implements GenericDAO<AircraftTypes> {
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
    public AircraftTypes add(AircraftTypes aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(aircraftTypes);
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public AircraftTypes getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        AircraftTypes aircraftTypes = session.get(AircraftTypes.class, id);
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public List<AircraftTypes> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<AircraftTypes> aircraftTypesList = session.createQuery("Select a from "
                + AircraftTypes.class.getSimpleName() + " a", AircraftTypes.class).getResultList();
        transaction.commit();
        session.close();
        return aircraftTypesList;
    }

    @Override
    public AircraftTypes update(AircraftTypes aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(aircraftTypes);
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public boolean delete(AircraftTypes aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        AircraftTypes persistance = session.load(AircraftTypes.class, aircraftTypes.getId());
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
