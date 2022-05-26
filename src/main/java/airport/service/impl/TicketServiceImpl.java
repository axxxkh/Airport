package airport.service.impl;

import airport.DAO.TicketDAO;
import airport.DAO.impl.TicketDAOImpl;
import airport.entity.*;
import airport.repository.*;
import airport.repository.impl.FlightRepositoryImpl;
import airport.repository.impl.PassengerRepositoryImpl;
import airport.repository.impl.PassportRepositoryImpl;
import airport.repository.impl.TicketRepositoryImpl;
import airport.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class TicketServiceImpl implements TicketService {
    @Autowired
    private TicketRepository ticketRepository = new TicketRepositoryImpl(new TicketDAOImpl());
    private AircraftRepository aircraftRepository;
    private PassengerRepository passengerRepository = new PassengerRepositoryImpl();
    private PassportRepository passportRepository = new PassportRepositoryImpl();
    private FlightRepository flightRepository = new FlightRepositoryImpl();


    @Override
    public List<Ticket> generateAndWriteTicketsForFlight(Flight flight) {
        Aircraft aircraft = flight.getCraftId();
        AircraftType aircraftTypes = aircraft.getTypeId();
        List<Ticket> ticketList = IntStream.rangeClosed(1, aircraftTypes.getCapacity())
                .mapToObj(seat -> Ticket
                        .builder()
                        .seat(seat)
                        .flight(flight)
                        .ticketStatus(TICKET_STATUS_NOT_SOLD)
                        .active(true)
                        .build())
                .collect(Collectors.toList());
        ticketRepository.addAll(ticketList);
        return ticketList;
    }

    @Override
    public void updateTicketsFlightFinished(Flight flight) {
        TicketDAO ticketDAO = new TicketDAOImpl();
        List<Ticket> ticketList = ticketDAO.getTicketsByFlight(flight);
        ticketList.forEach(t -> t.setTicketStatus(TICKET_STATUS_FINISHED));
        ticketDAO.updateAll(ticketList);
    }

    @Override
    public Ticket buyTicket(Passenger passenger, int flightNumber) {

        Queue<Passport> passportList = new LinkedList<>(passenger.getPassports());
        Passport passport = passportList.peek();
        Passport passportDB = passportRepository.getBySerialNumber(passport.getSerialNumber());
        Passenger passengerDB = passengerRepository.getByPassport(passportDB).orElseThrow();
        Flight flight = flightRepository.getFlightByNumber(flightNumber);
        Queue<Ticket> ticketQueue = new LinkedList<>(getAvailableTickets(flight));
        Ticket ticket = ticketQueue.peek();
        if (ticket != null) {
            ticket.setPassenger(passengerDB);
            ticket.setBuyDate(LocalDate.now());
            ticketRepository.update(ticket);
            return ticket;
        }
        return null;
    }

    @Override
    public List<Ticket> getAvailableTickets(Flight flight) {

        return ticketRepository.getTicketsByFlight(flight)
                .stream()
                .filter(t -> (t.isActive() && t.getTicketStatus() == TICKET_STATUS_NOT_SOLD))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getAllAvailableTickets() {
        return ticketRepository.getAllActive()
                .stream()
                .filter(t -> (t.isActive() && t.getTicketStatus() == TICKET_STATUS_NOT_SOLD))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getAllBuyedTicketsByPeriod(LocalDate startDate, LocalDate endDate) {

        return ticketRepository
                .getAll()
                .stream()
                .filter(t -> (t.getBuyDate().isAfter(startDate)
                        && t.getBuyDate().isBefore(endDate)))
                .filter(t -> t.getTicketStatus() == TICKET_STATUS_SOLD)
                .collect(Collectors.toList());
    }
}
