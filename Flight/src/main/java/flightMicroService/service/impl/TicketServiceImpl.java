package flightMicroService.service.impl;

import flightMicroService.dto.FlightDTO;
import flightMicroService.dto.PassengerDTO;
import flightMicroService.dto.PassportDTO;
import flightMicroService.dto.TicketDTO;
import flightMicroService.entity.*;
import flightMicroService.repository.*;
import flightMicroService.service.TicketService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
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

    private TicketRepository ticketRepository;
    private AircraftRepository aircraftRepository;
    private PassengerRepository passengerRepository;
    private PassportRepository passportRepository;
    private FlightRepository flightRepository;
    private ModelMapper mapper;


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
        ticketRepository.saveAllAndFlush(ticketList);
        return ticketList;
    }

    @Override
    public void updateTicketsFlightFinished(Flight flight) {
        List<Ticket> ticketList = ticketRepository.findTicketsByFlightId(flight.getId());
        ticketList.forEach(t -> t.setTicketStatus(TICKET_STATUS_FINISHED));
        ticketRepository.saveAllAndFlush(ticketList);
    }

    @Override
    public void updateTicketsFlightCancelled(Iterable<Ticket> tickets) {
        tickets.forEach(ticket -> ticket.setTicketStatus(TICKET_CANCELLED));
    }

    @Override
    public TicketDTO buyTicket(PassengerDTO passengerDTO, int flightNumber) {

        Queue<PassportDTO> passportList = new LinkedList<>(passengerDTO.getPassports());
        assert passportList.peek() != null;
        Passport passport = passportRepository.findBySerialNumber(passportList.peek().getSerialNumber());
        assert passport != null;
        Passport passportDB = passportRepository.findBySerialNumber(passport.getSerialNumber());
        Passenger passengerDB = passportDB.getPassenger();
        Flight flight = flightRepository.findByFlightNumber(flightNumber);
        ticketRepository.findTicketsByFlightId(flight.getId());
        Queue<Ticket> ticketQueue = new LinkedList<>(ticketRepository.findTicketsByFlightId(flight.getId()));
        Ticket ticket = ticketQueue.peek();
        if (ticket != null) {
            ticket.setPassenger(passengerDB);
            ticket.setBuyDate(LocalDate.now());
            ticket.setTicketStatus(TICKET_STATUS_SOLD);
            ticketRepository.saveAndFlush(ticket);
            return mapper.map(ticket, TicketDTO.class);
        }
        return null;
    }

    @Override
    public List<TicketDTO> getAvailableTickets(FlightDTO flightDTO) {
        Flight flight = flightRepository.findByFlightNumber(flightDTO.getFlightNumber());

        return ticketRepository.findTicketsByFlightId(flight.getId())
                .stream()
                .filter(t -> (t.isActive() && t.getTicketStatus() == TICKET_STATUS_NOT_SOLD))
                .map(t -> mapper.map(t, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDTO> getAllAvailableTickets() {
        return ticketRepository.findByActiveTrue()
                .stream()
                .filter(t -> (t.isActive() && t.getTicketStatus() == TICKET_STATUS_NOT_SOLD))
                .map(t -> mapper.map(t, TicketDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<TicketDTO> getAllBuyedTicketsByPeriod(LocalDate startDate, LocalDate endDate) {

        return ticketRepository
                .findAll()
                .stream()
                .filter(t -> (t.getBuyDate().isAfter(startDate)
                        && t.getBuyDate().isBefore(endDate)))
                .filter(t -> t.getTicketStatus() == TICKET_STATUS_SOLD)
                .map(t -> mapper.map(t, TicketDTO.class))
                .collect(Collectors.toList());
    }
}
