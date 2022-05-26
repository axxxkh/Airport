package airport.service.impl;

import airport.entity.Flight;
import airport.entity.Ticket;
import airport.repository.FlightRepository;
import airport.repository.TicketRepository;
import airport.service.FlightService;
import airport.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;
    private TicketRepository ticketRepository;
    private TicketService ticketService;

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
