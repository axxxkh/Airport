package airport.service.impl;

import airport.dto.RouteDTO;
import airport.repository.FlightRepository;
import airport.repository.RouteRepository;
import airport.entity.Flight;
import airport.entity.Route;
import airport.service.RouteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
