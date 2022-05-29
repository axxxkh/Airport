package airport.service.impl;

import airport.entity.Flight;
import airport.entity.Ticket;
import airport.repositoryDAO.FlightRepositoryDAO;
import airport.repositoryDAO.TicketRepositoryDAO;
import airport.service.FlightEntityService;
import airport.service.TicketEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightDAOServiceImpl implements FlightEntityService {

    @Autowired
    private FlightRepositoryDAO flightRepository;
    private TicketRepositoryDAO ticketRepository;
    private TicketEntityService ticketService;

    @Override
    public void flightFinished(Flight flight) {
        List<Ticket> ticketList = ticketRepository.getTicketsByFlight(flightRepository.getById(1).orElseThrow());
        flight.setFlightStatus(FLIGHT_STATUS_FINISHED);
        flightRepository.update(flight);
        ticketService.updateTicketsFlightFinished(flight);
    }

    @Override
    public List<Flight> getAllActiveFlights() {
        return flightRepository.getAllActive();
    }

    @Override
    public List<Flight> getFlightsByPeriod(LocalDate startDate, LocalDate endDate) {
        return flightRepository
                .getAllActive()
                .stream()
                .filter(f -> f.getTime().toLocalDate().isAfter(startDate)
                        && f.getTime().toLocalDate().isBefore(endDate))
                .collect(Collectors.toList());
    }
}
