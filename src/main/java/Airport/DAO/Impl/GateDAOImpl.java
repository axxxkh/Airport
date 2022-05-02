package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import Airport.Entity.Gate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class GateDAOImpl implements GenericDAO<Gate> {
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
    public Gate add(Gate gate) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(gate);
        transaction.commit();
        session.close();
        return gate;
    }

    @Override
    public Gate getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Gate gate = session.get(Gate.class, id);
        transaction.commit();
        session.close();
        return gate;
    }

    @Override
    public Gate update(Gate gate) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(gate);
        transaction.commit();
        session.close();
        return gate;
    }

    @Override
    public boolean delete(Gate gate) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Gate persistance = session.load(Gate.class, gate.getId());
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
    public List<Gate> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Gate> gateList = session.createQuery("Select a from " + Gate.class.getSimpleName()
                + " a", Gate.class).getResultList();
        session.close();
        return gateList;
    }
}
