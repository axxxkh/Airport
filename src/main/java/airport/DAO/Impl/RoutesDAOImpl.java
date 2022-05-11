package airport.DAO.Impl;

import airport.DAO.RoutesDAO;
import airport.entity.Route;
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
    public Route add(Route route) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(route);
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public Optional<Route> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional<Route> route = Optional.ofNullable(session.get(Route.class, id));
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public Route update(Route route) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(route);
        transaction.commit();
        session.close();
        return route;
    }

    @Override
    public boolean delete(Route route) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Route routeFromDB = session.load(Route.class, route.getId());
        if (routeFromDB != null) {
            routeFromDB.setActive(false);
            session.update(routeFromDB);
            transaction.commit();
            session.close();
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public List<Route> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Route> routeList = session.createQuery("Select a from "
                + Route.class.getSimpleName()
                + " a", Route.class).getResultList();
        transaction.commit();
        session.close();
        return routeList;
    }

    @Override
    public List<Route> getAllActive() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Route> routeList = session.createQuery("From "
                + Route.class.getSimpleName()
                + " r where r.active = true", Route.class).getResultList();
        transaction.commit();
        session.close();
        return routeList;
    }
}
