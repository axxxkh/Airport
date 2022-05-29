package airport.service.impl;

import airport.DTO.FlightDTO;
import airport.Repository.FlightRepository;
import airport.Repository.TicketRepository;
import airport.entity.Flight;
import airport.entity.Ticket;
import airport.service.FlightService;
import airport.service.TicketService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private TicketService ticketService;
    private ModelMapper mapper;

    @Override
    public void flightFinished(Flight flight) {
        List<Ticket> ticketList = ticketRepository.findTicketsByFlightId(flight.getId());
        flight.setFlightStatus(FLIGHT_STATUS_FINISHED);
        flightRepository.saveAndFlush(flight);
        ticketService.updateTicketsFlightFinished(flight);
    }

    @Override
    public List<FlightDTO> getAllActiveFlights() {
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
}
