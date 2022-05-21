package airport.DAO.impl;

import airport.DAO.PassportDAO;
import airport.entity.Passport;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;
import java.util.Optional;

public class PassportDAOImpl implements PassportDAO {
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
    public Passport add(Passport passport) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(passport);
        transaction.commit();
        session.close();
        return passport;
    }

    @Override
    public Optional<Passport> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Passport> passport = Optional.ofNullable(session.get(Passport.class, id));
        transaction.commit();
        session.close();
        return passport;
    }

    @Override
    public List<Passport> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Passport> passportList = session.createQuery("Select a from "
                + Passport.class.getSimpleName()
                + " a", Passport.class).getResultList();
        transaction.commit();
        session.close();
        return passportList;
    }

    @Override
    public List<Passport> getAllActive() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Query<Passport> query = session.createQuery("from Passport p " +
                "where p.active=true", Passport.class);
        List<Passport> passportList = query.getResultList();
        transaction.commit();
        session.close();
        return passportList;
    }

    @Override
    public Passport update(Passport passport) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(passport);
        transaction.commit();
        session.close();
        return passport;
    }

    @Override
    public boolean delete(Passport passport) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Passport passportFromDB = session.load(Passport.class, passport.getId());
        if (passportFromDB != null) {
            passport.setActive(false);
            session.update(passportFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public Passport getBySerialNumber(String serialNumber) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Passport passportDB = session.bySimpleNaturalId(Passport.class).load(serialNumber);
        transaction.commit();
        session.close();
        return passportDB;
    }
}
