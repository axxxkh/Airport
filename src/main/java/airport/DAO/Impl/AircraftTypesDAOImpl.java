package airport.DAO.Impl;

import airport.DAO.AircraftTypesDAO;
import airport.entity.AircraftTypes;
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
    public AircraftTypes add(AircraftTypes aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(aircraftTypes);
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public Optional <AircraftTypes> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <AircraftTypes> aircraftTypes = Optional.ofNullable(session.get(AircraftTypes.class, id));
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
        session.update(aircraftTypes);
        transaction.commit();
        session.close();
        return aircraftTypes;
    }

    @Override
    public boolean delete(AircraftTypes aircraftTypes) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        AircraftTypes aircraftTypesFromDB = session.load(AircraftTypes.class, aircraftTypes.getId());
        if (aircraftTypesFromDB != null) {
            session.delete(aircraftTypesFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
