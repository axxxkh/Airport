package airport.repository.impl;

import airport.DAO.GateDAO;
import airport.DAO.impl.GateDAOImpl;
import airport.entity.Gate;
import airport.repository.GateRepository;

import java.util.List;
import java.util.Optional;

public class GateRepositoryImpl implements GateRepository {
    private GateDAO gateDAO = new GateDAOImpl();

    @Override
    public Gate add(Gate gate) {
        return gateDAO.add(gate);
    }

    @Override
    public Optional<Gate> getById(int id) {
        return gateDAO.getById(id);
    }

    @Override
    public List<Gate> getAll() {
        return gateDAO.getAll();
    }

    @Override
    public List<Gate> getAllActive() {
        return gateDAO.getAllActive();
    }

    @Override
    public Gate update(Gate gate) {
        return gateDAO.update(gate);
    }

    @Override
    public boolean delete(Gate gate) {
        return gateDAO.delete(gate);
    }
}
