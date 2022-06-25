package flightMicroService.service.impl;

import flightMicroService.dto.FlightDTO;
import flightMicroService.dto.RouteDTO;
import flightMicroService.entity.Flight;
import flightMicroService.entity.Route;
import flightMicroService.entity.Ticket;
import flightMicroService.exceptions.EntityNotFoundException;
import flightMicroService.repository.FlightRepository;
import flightMicroService.repository.RouteRepository;
import flightMicroService.repository.TicketRepository;
import flightMicroService.service.FlightService;
import flightMicroService.service.TicketService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private RouteRepository routeRepository;
    private TicketService ticketService;
    private ModelMapper mapper;

    @Override
    public FlightDTO flightFinished(FlightDTO flightDTO) {
        Flight flight = flightRepository.findByFlightNumber(flightDTO.getFlightNumber()).orElseThrow();
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

//    @Override
//    public List<FlightDTO> getFlightsByDate(LocalDateTime time) {
//        return flightRepository.findFlightsByTime(date)
//                .stream()
//                .map(f -> mapper.map(f, FlightDTO.class))
//                .collect(Collectors.toList());
//    }

    @Override
    public FlightDTO updateDate(FlightDTO flightDTO, LocalDateTime newDateTime) {
        Flight flight = flightRepository.findByFlightNumber(flightDTO.getFlightNumber()).orElseThrow();
        flight.setTime(newDateTime);
        return mapper.map(flightRepository.save(flight), FlightDTO.class);
    }

    @Override
    public FlightDTO getByNumber(int flightNumber) {
        Flight flight = flightRepository
                .findByFlightNumber(flightNumber)
                .orElseThrow(() -> new EntityNotFoundException("Flight number " + flightNumber + " not found"));
        return mapper.map(flight, FlightDTO.class);
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
        Flight flight = flightRepository.findByFlightNumber(flightDTO.getFlightNumber()).orElseThrow();
//        List<Ticket>
        flightRepository.delete(flight);
    }

    @Override
    public List<FlightDTO> getByDate(LocalDate date) {
        return flightRepository.findByDate(date)
                .stream()
                .map(flight -> mapper.map(flight, FlightDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteFlight(int flightNumber) {
        Flight flight = flightRepository.findByFlightNumber(flightNumber).orElseThrow();
        flightRepository.delete(flight);
    }

    @Override
    public void addFlight(FlightDTO flightDTO) {
        Flight flight = mapper.map(flightDTO, Flight.class);
        flightRepository.save(flight);
    }


}
