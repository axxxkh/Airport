package Airport.DAO.Impl;

import Airport.DAO.GenericDAO;
import Airport.Entity.Aircraft;
import Airport.Entity.Route;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class RouteDAOImpl{
//    private static SessionFactory sessionFactory;
//
//    private static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            sessionFactory = new Configuration()
//                    .configure()
//                    .buildSessionFactory();
//        }
//        return sessionFactory;
//    }
//
//    @Override
//    public Route add(Route route) {
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.save(route);
//        transaction.commit();
//        session.close();
//        return route;
//    }
//
//    @Override
//    public Route getById(int id) {
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Route route = session.get(Route.class, id);
//        transaction.commit();
//        session.close();
//        return route;
//    }
//
//    @Override
//    public Route update(Route route) {
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        session.saveOrUpdate(route);
//        transaction.commit();
//        session.close();
//        return route;
//    }
//
//    @Override
//    public boolean delete(Route route) {
//        Session session = getSessionFactory().openSession();
//        Transaction transaction = session.beginTransaction();
//        Route persistance = session.load(Route.class, route.getId());
//        if (persistance != null) {
//            session.delete(persistance);
//            transaction.commit();
//            session.close();
//            return true;
//        }
//        session.close();
//        return false;    }
}
