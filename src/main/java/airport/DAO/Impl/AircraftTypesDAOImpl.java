package airport.DAO.Impl;

import airport.DAO.AircraftTypesDAO;
import airport.entity.AircraftType;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;


public class AircraftTypesDAOImpl implements AircraftTypesDAO {
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
    public AircraftType add(AircraftType aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(aircraftTypes);
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public Optional<AircraftType> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<AircraftType> aircraftTypes = Optional.ofNullable(session.get(AircraftType.class, id));
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public List<AircraftType> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<AircraftType> aircraftTypesList = session.createQuery("Select a from "
                + AircraftType.class.getSimpleName() + " a", AircraftType.class).getResultList();
        transaction.commit();
        session.close();
        return aircraftTypesList;
    }

    @Override
    public List<AircraftType> getAllActive() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<AircraftType> aircraftTypesList = session.createQuery("From AircraftTypes a " +
                "where a.active=true", AircraftType.class).getResultList();
        transaction.commit();
        session.close();
        return aircraftTypesList;
    }

    @Override
    public AircraftType update(AircraftType aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(aircraftTypes);
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public boolean delete(AircraftType aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        AircraftType aircraftTypesFromDB = session.load(AircraftType.class, aircraftTypes.getId());
        if (aircraftTypesFromDB != null) {
            aircraftTypesFromDB.setActive(false);
            session.update(aircraftTypesFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
