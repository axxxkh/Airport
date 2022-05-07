package airport.DAO.Impl;

import airport.DAO.AvialineDAO;
import airport.entity.Avialine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class AvialineDAOImpl implements AvialineDAO {
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
    public Optional <Avialine> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Avialine> avialine = Optional.ofNullable(session.get(Avialine.class, id));
        transaction.commit();
        session.close();
        return avialine;
    }

    @Override
    public Avialine update(Avialine avialine) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(avialine);
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
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<Avialine> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Avialine> avialineList = session.createQuery("Select a from " + Avialine.class.getSimpleName()
                + " a", Avialine.class).getResultList();
        transaction.commit();
        session.close();
        return avialineList;
    }
}
