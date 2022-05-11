package airport.DAO.Impl;

import airport.DAO.GenericDAO;
import airport.entity.Personal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class PersonalDAOImpl implements GenericDAO<Personal> {
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
    public Personal add(Personal personal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(personal);
        transaction.commit();
        session.close();
        return personal;
    }

    @Override
    public Optional<Personal> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Personal> personal = Optional.ofNullable(session.get(Personal.class, id));
        transaction.commit();
        session.close();
        return personal;
    }

    @Override
    public List<Personal> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Personal> personalList = session.createQuery("Select a from "
                + Personal.class.getSimpleName()
                + " a", Personal.class).getResultList();
        transaction.commit();
        session.close();
        return personalList;
    }

    @Override
    public List<Personal> getAllActive() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Personal> personalList = session.createQuery("From "
                + Personal.class.getSimpleName()
                + " p where p.active = true", Personal.class).getResultList();
        transaction.commit();
        session.close();
        return personalList;
    }

    @Override
    public Personal update(Personal personal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(personal);
        transaction.commit();
        session.close();
        return personal;
    }

    @Override
    public boolean delete(Personal personal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Personal personalFromDB = session.get(Personal.class, personal.getId());
        if (personalFromDB != null) {
            session.delete(personalFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
