package airport.DAO.Impl;

import airport.DAO.GateDAO;
import airport.entity.Gate;
import airport.entity.Terminal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class GateDAOImpl implements GateDAO {
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
    public Optional <Gate> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Gate> gate = Optional.ofNullable(session.get(Gate.class, id));
        transaction.commit();
        session.close();
        return gate;
    }

    @Override
    public Gate update(Gate gate) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(gate);
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
        transaction.commit();
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

    @Override
    public List<Gate> getGatesByTerminal(Terminal terminal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query <Gate> query = session.createQuery("from Gate g " +
                "LEFT JOIN FETCH g.terminal t" +
                "where t.id =:id");
        query.setParameter("id", terminal.getId());
        List<Gate> gateList = query.getResultList();
        transaction.commit();
        session.close();
        return gateList;
    }
}
