package airport.repositoryDAO.impl;

import airport.DAO.RoutesDAO;
import airport.DAO.impl.RoutesDAOImpl;
import airport.entity.Route;
import airport.repositoryDAO.RouteRepositoryDAO;

import java.util.List;
import java.util.Optional;

public class RouteRepositoryImplDAO implements RouteRepositoryDAO {
    private final RoutesDAO routesDAO = new RoutesDAOImpl();

    @Override
    public Route add(Route route) {
        return routesDAO.add(route);
    }

    @Override
    public Optional<Route> getById(int id) {
        return routesDAO.getById(id);
    }

    @Override
    public List<Route> getAll() {
        return routesDAO.getAll();
    }

    @Override
    public List<Route> getAllActive() {
        return routesDAO.getAllActive();
    }

    @Override
    public Route update(Route route) {
        return routesDAO.update(route);
    }

    @Override
    public boolean delete(Route route) {
        return routesDAO.delete(route);
    }
}
