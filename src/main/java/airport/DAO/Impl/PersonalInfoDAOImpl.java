package airport.DAO.Impl;

import airport.DAO.GenericDAO;
import airport.entity.PersonalInfo;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class PersonalInfoDAOImpl implements GenericDAO<PersonalInfo> {
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
    public PersonalInfo add(PersonalInfo personalInfo) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(personalInfo);
        transaction.commit();
        session.close();
        return personalInfo;
    }

    @Override
    public Optional<PersonalInfo> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<PersonalInfo> personalInfo = Optional.ofNullable(session.get(PersonalInfo.class,id));
        return personalInfo;
    }

    @Override
    public List<PersonalInfo> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<PersonalInfo> personalInfoList = session.createQuery("from PersonalInfo p").getResultList();
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public PersonalInfo update(PersonalInfo personalInfo) {
        Session session =getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(personalInfo);
        transaction.commit();
        session.close();
        return personalInfo;
    }

    @Override
    public boolean delete(PersonalInfo personalInfo) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        PersonalInfo getPersonalInfo = session.get(PersonalInfo.class, personalInfo.getId());
        if (getPersonalInfo!=null) {
            session.delete(getPersonalInfo);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
