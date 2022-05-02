package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.lang.reflect.ParameterizedType;
import java.util.List;

public class DAOImpl<T> implements GenericDAO<T> {
    private final Class<T> entityClass;
    private static SessionFactory sessionFactory;

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public DAOImpl() {
        ParameterizedType superClass = (ParameterizedType) getClass().getGenericSuperclass();
        this.entityClass = (Class<T>) superClass.getActualTypeArguments()[0];
    }

    @Override
    public T add(T t) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
        return t;
    }

    @Override
    public T getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        T t = session.find(entityClass, id);
        transaction.commit();
        session.close();
        return t;
    }

    @Override
    public List<T> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<T> allEntity = session.createQuery("Select a from " + entityClass.getSimpleName() + " a", entityClass).getResultList();
        return allEntity;
    }

    @Override
    public T update(T t) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(t);
        transaction.commit();
        session.close();
        return null;
    }

    @Override
    public boolean delete(T t) {
        return false;
    }
}
