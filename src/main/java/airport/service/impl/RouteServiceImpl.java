package airport.service.impl;

import airport.DTO.RouteDTO;
import airport.Repository.FlightRepository;
import airport.Repository.RouteRepository;
import airport.entity.Flight;
import airport.entity.Route;
import airport.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;
    private FlightRepository flightRepository;

    @Override
    public void delete(RouteDTO routeDTO) {
        Route route = routeRepository.findByName(routeDTO.getName());
        List<Flight> flightList = flightRepository.findByRoute(route);
        flightRepository.deleteAll(flightList);
        routeRepository.delete(route);
    }
}
