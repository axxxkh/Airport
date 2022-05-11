package airport.DAO.Impl;

import airport.DAO.RoutesDAO;
import airport.entity.Routes;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class RoutesDAOImpl implements RoutesDAO {
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
    public Routes add(Routes route) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(route);
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public Optional<Routes> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Routes> route = Optional.ofNullable(session.get(Routes.class, id));
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public Routes update(Routes route) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(route);
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public boolean delete(Routes route) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Routes routesFromDB = session.load(Routes.class, route.getId());
        if (routesFromDB != null) {
            session.delete(routesFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<Routes> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Routes> routeList = session.createQuery("Select a from "
                + Routes.class.getSimpleName()
                + " a", Routes.class).getResultList();
        transaction.commit();
        session.close();
        return routeList;
    }

    @Override
    public List<Routes> getAllActive() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Routes> routeList = session.createQuery("From "
                + Routes.class.getSimpleName()
                + " r where r.active = true", Routes.class).getResultList();
        transaction.commit();
        session.close();
        return routeList;
    }
}
