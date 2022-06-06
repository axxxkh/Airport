package airport.service.impl;

import airport.dto.RouteDTO;
import airport.entity.Flight;
import airport.entity.Route;
import airport.entity.Ticket;
import airport.repository.FlightRepository;
import airport.repository.RouteRepository;
import airport.repository.TicketRepository;
import airport.service.RouteService;
import airport.service.TicketService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private TicketService ticketService;

    @Override
    public void delete(RouteDTO routeDTO) {
        Route route = routeRepository.findByName(routeDTO.getName());
        List<Flight> flightList = flightRepository.findByRoute(route);

        List<Ticket> ticketList = flightList.stream()
                .map(flight -> ticketRepository.findTicketsByFlightId(flight.getId()))
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
        ticketService.updateTicketsFlightCancelled(ticketList);
        flightRepository.deleteAll(flightList);
        routeRepository.delete(route);
    }
}
