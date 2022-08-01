package flightMicroService.service.impl;

import flightMicroService.dto.RouteDTO;
import flightMicroService.entity.Flight;
import flightMicroService.entity.Route;
import flightMicroService.entity.Ticket;
import flightMicroService.exceptions.EntityNotFoundException;
import flightMicroService.repository.FlightRepository;
import flightMicroService.repository.RouteRepository;
import flightMicroService.repository.TicketRepository;
import flightMicroService.service.RouteService;
import flightMicroService.service.TicketService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RouteServiceImpl implements RouteService {

    private RouteRepository routeRepository;
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private TicketService ticketService;

    @Override
    public void delete(RouteDTO routeDTO) {
        Route route = routeRepository.findByName(routeDTO.getName())
                .orElseThrow(() -> new EntityNotFoundException("Route " + routeDTO.getName() + "doesn't found"));
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
