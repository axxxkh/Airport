package airport.service.impl;

import airport.dto.FlightDTO;
import airport.dto.RouteDTO;
import airport.entity.Flight;
import airport.entity.Route;
import airport.entity.Ticket;
import airport.repository.FlightRepository;
import airport.repository.RouteRepository;
import airport.repository.TicketRepository;
import airport.service.FlightService;
import airport.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private TicketService ticketService;
    private ModelMapper mapper;
    private RouteRepository routeRepository;

    @Override
    public FlightDTO flightFinished(FlightDTO flightDTO) {
        Flight flight = flightRepository.findByFlightNumber(flightDTO.getFlightNumber());
        List<Ticket> ticketList = ticketRepository.findTicketsByFlightId(flight.getId());
        flight.setFlightStatus(FLIGHT_STATUS_FINISHED);
        flightRepository.saveAndFlush(flight);
        ticketService.updateTicketsFlightFinished(flight);
        return mapper.map(flight, FlightDTO.class);
    }

    @Override
    public List<FlightDTO> getAllFlights() {
        return flightRepository.findByActiveTrue()
                .stream()
                .map(f -> mapper.map(f, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDTO> getFlightsByPeriod(LocalDate startDate, LocalDate endDate) {
        return flightRepository
                .findByActiveTrue()
                .stream()
                .filter(f -> f.getTime().toLocalDate().isAfter(startDate)
                        && f.getTime().toLocalDate().isBefore(endDate))
                .map(f -> mapper.map(f, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<FlightDTO> getFlightsByDate(LocalDate date) {
        return flightRepository.findFlightsByDate(date)
                .stream()
                .map(f -> mapper.map(f, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public FlightDTO updateDate(FlightDTO flightDTO, LocalDateTime newDateTime) {
        Flight flight = flightRepository.findByFlightNumber(flightDTO.getFlightNumber());
        flight.setTime(newDateTime);
        return mapper.map(flightRepository.save(flight), FlightDTO.class);
    }

    @Override
    public FlightDTO getByNumber(int flightNumber) {
        return mapper.map(flightRepository.findByFlightNumber(flightNumber),FlightDTO.class);
    }

    @Override
    public List<FlightDTO> getFlightsByRoutes(RouteDTO routeDTO) {
        Route route = routeRepository.findByName(routeDTO.getName());
        return flightRepository.findByRoute(route)
                .stream()
                .map(f -> mapper.map(f, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFlight(FlightDTO flightDTO) {
        Flight flight =flightRepository.findByFlightNumber(flightDTO.getFlightNumber());
        flightRepository.delete(flight);
    }
}
