package airport.repository.impl;

import airport.DAO.AircraftDAO;
import airport.DAO.impl.AircraftDAOImpl;
import airport.entity.Aircraft;
import airport.repository.AircraftRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class AircraftRepositoryImpl implements AircraftRepository {
    private AircraftDAO aircraftDAO = new AircraftDAOImpl();

    @Override
    public Aircraft add(Aircraft aircraft) {
        return aircraftDAO.add(aircraft);
    }

    @Override
    public Optional<Aircraft> getById(int id) {
        return aircraftDAO.getById(id);
    }

    @Override
    public List<Aircraft> getAll() {
        return aircraftDAO.getAll();
    }

    @Override
    public List<Aircraft> getAllActive() {
        return aircraftDAO.getAllActive();
    }

    @Override
    public Aircraft update(Aircraft aircraft) {
        return aircraftDAO.update(aircraft);
    }

    @Override
    public boolean delete(Aircraft aircraft) {
        return aircraftDAO.delete(aircraft);
    }
}
