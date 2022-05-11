package airport.DAO.Impl;

import airport.DAO.GenericDAO;
import airport.entity.Salary;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class SalaryDAOImpl implements GenericDAO<Salary> {

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
    public Salary add(Salary salary) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(salary);
        transaction.commit();
        session.close();
        return salary;
    }

    @Override
    public Optional<Salary> getById(int id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Salary> salary = Optional.ofNullable(session.get(Salary.class, id));
        transaction.commit();
        session.close();
        return Optional.empty();
    }

    @Override
    public List<Salary> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Salary> salaryList = session.createQuery("from Salary s", Salary.class).getResultList();
        transaction.commit();
        session.close();
        return salaryList;
    }

    @Override
    public List<Salary> getAllActive() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Salary> salaryList = session.createQuery("From Salary s " +
                "s.active = true", Salary.class).getResultList();
        transaction.commit();
        session.close();
        return salaryList;
    }

    @Override
    public Salary update(Salary salary) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(salary);
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public boolean delete(Salary salary) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Salary salaryFromDB = session.load(Salary.class, salary.getId());
        if (salaryFromDB != null) {
            session.delete(salaryFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }
}
