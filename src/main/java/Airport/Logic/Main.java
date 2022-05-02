package Airport.Logic;

import Airport.DAO.GenericDAO;
import Airport.DAO.Impl.*;
import Airport.Entity.*;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        GenericDAO<Aircraft> aircraftGenericDAO = new AircraftDAOImpl();
        GenericDAO<AircraftTypes> aircraftTypesGenericDAO = new AircraftTypesDAOImpl();
        GenericDAO<Avialine> avialineGenericDAO = new AvialineDAOImpl();
        GenericDAO<Flight> flightGenericDAO = new FlightDAOImpl();
        GenericDAO<Gate> gateGenericDAO = new GateDAOImpl();
        GenericDAO<Passenger> passengerGenericDAO = new PassengerDAOImpl();
        GenericDAO<Routes> routeGenericDAO = new RoutesDAOImpl();
        GenericDAO<Terminal> terminalGenericDAO = new TerminalDAOImpl();
        GenericDAO<Ticket> ticketGenericDAO = new TicketDAOImpl();

//        Ticket myTicket = Ticket.builder()
//                .number(112111189)
//                .build();
//        ticketGenericDAO.add(myTicket);
//        Ticket changeTicket = Ticket.builder()
//                .id(11)
//                .number(99999999)
//                .build();
//        ticketGenericDAO.update(changeTicket);

        List<Aircraft> aircraftList = aircraftGenericDAO.getAll();
        List<Avialine> avialineList = avialineGenericDAO.getAll();
        List<AircraftTypes> aircraftTypesList = aircraftTypesGenericDAO.getAll();
        List<Flight> flightList = flightGenericDAO.getAll();
        List<Gate> gateList = gateGenericDAO.getAll();
        List<Passenger> passengerList = passengerGenericDAO.getAll();
        List<Routes> routeList = routeGenericDAO.getAll();
        List<Terminal> terminalList = terminalGenericDAO.getAll();
        List<Ticket> ticketList = ticketGenericDAO.getAll();


        aircraftList.forEach(System.out::println);
        aircraftTypesList.forEach(System.out::println);
        avialineList.forEach(System.out::println);
        flightList.forEach(System.out::println);
        gateList.forEach(System.out::println);
        passengerList.forEach(System.out::println);
        routeList.forEach(System.out::println);
        terminalList.forEach(System.out::println);
        ticketList.forEach(System.out::println);

        Ticket ticketById = ticketGenericDAO.getById(11);
        System.out.println(ticketById);

//        ComplexLogic.getPassengersByFlight(flightGenericDAO.getById(1));
//        ComplexLogic.getTicketsByFlight(flightGenericDAO.getById(1));
        ComplexLogic.getFlightsByAvialine(avialineGenericDAO.getById(3));

        Passenger newPassanger = Passenger.builder()
                .birthdate(LocalDate.of(1990, 10, 10))
                .name("Jack")
                .surname("Daniels")
                .passport("EA123456")
                .build();
//        passengerGenericDAO.add(newPassanger);

        ComplexLogic.buyTicket(newPassanger, flightGenericDAO.getById(1));
        ComplexLogic.getAllFlightsByTerminal(terminalGenericDAO.getById(1)).forEach(System.out::println);

    }
}
