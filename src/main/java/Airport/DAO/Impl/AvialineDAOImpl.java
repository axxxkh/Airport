package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import Airport.Entity.Aircraft;
import Airport.Entity.Avialine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class AvialineDAOImpl implements GenericDAO<Avialine> {
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
    public Avialine add(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(avialine);
        transaction.commit();
        session.close();
        return avialine;
    }

    @Override
    public Avialine getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Avialine avialine = session.get(Avialine.class, id);
        transaction.commit();
        session.close();
        return avialine;
    }

    @Override
    public Avialine update(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(avialine);
        transaction.commit();
        session.close();
        return avialine;
    }

    @Override
    public boolean delete(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Avialine persistance = session.load(Avialine.class, avialine.getId());
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
    public List<Avialine> getAll() {
        return null;
    }
}
