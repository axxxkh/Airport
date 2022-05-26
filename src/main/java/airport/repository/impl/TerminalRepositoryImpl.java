package airport.repository.impl;

import airport.DAO.TerminalDAO;
import airport.DAO.impl.TerminalDAOImpl;
import airport.entity.Terminal;
import airport.repository.TerminalRepository;

import java.util.List;
import java.util.Optional;

public class TerminalRepositoryImpl implements TerminalRepository {
    private final TerminalDAO terminalDAO = new TerminalDAOImpl();

    @Override
    public Terminal add(Terminal terminal) {
        return terminalDAO.add(terminal);
    }

    @Override
    public Optional<Terminal> getById(int id) {
        return terminalDAO.getById(id);
    }

    @Override
    public List<Terminal> getAll() {
        return terminalDAO.getAll();
    }

    @Override
    public List<Terminal> getAllActive() {
        return terminalDAO.getAllActive();
    }

    @Override
    public Terminal update(Terminal terminal) {
        return terminalDAO.update(terminal);
    }

    @Override
    public boolean delete(Terminal terminal) {
        return terminalDAO.delete(terminal);
    }
}
