package airport.repository.impl;

import airport.DAO.AircraftTypesDAO;
import airport.DAO.impl.AircraftTypesDAOImpl;
import airport.entity.AircraftType;
import airport.repository.AircraftTypeRepository;

import java.util.List;
import java.util.Optional;

public class AircraftTypeRepositoryImpl implements AircraftTypeRepository {
    private AircraftTypesDAO aircraftTypesDAO= new AircraftTypesDAOImpl();

    @Override
    public AircraftType add(AircraftType aircraftType) {
        return aircraftTypesDAO.add(aircraftType);
    }

    @Override
    public Optional<AircraftType> getById(int id) {
        return aircraftTypesDAO.getById(id);
    }

    @Override
    public List<AircraftType> getAll() {
        return aircraftTypesDAO.getAll();
    }

    @Override
    public List<AircraftType> getAllActive() {
        return aircraftTypesDAO.getAllActive();
    }

    @Override
    public AircraftType update(AircraftType aircraftType) {
        return aircraftTypesDAO.update(aircraftType);
    }

    @Override
    public boolean delete(AircraftType aircraftType) {
        return aircraftTypesDAO.delete(aircraftType);
    }
}
