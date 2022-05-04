package Airport.DAO.Impl;

import Airport.DAO.TerminalDAO;
import Airport.Entity.Terminal;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;
import java.util.Optional;

public class TerminalDAOImpl implements TerminalDAO {
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
    public Terminal add(Terminal terminal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(terminal);
        transaction.commit();
        session.close();
        return terminal;
    }

    @Override
    public Optional <Terminal> getById(int id) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Optional <Terminal> terminal = Optional.ofNullable(session.get(Terminal.class, id));
        transaction.commit();
        session.close();
        return terminal;
    }

    @Override
    public Terminal update(Terminal terminal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(terminal);
        transaction.commit();
        session.close();
        return terminal;
    }

    @Override
    public boolean delete(Terminal terminal) {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Terminal persistance = session.load(Terminal.class, terminal.getId());
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
    public List<Terminal> getAll() {
        Session session = getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        List<Terminal> terminalList = session.createQuery("Select a from " +
                Terminal.class.getSimpleName()
                + " a", Terminal.class).getResultList();
        session.close();
        return terminalList;
    }
}
