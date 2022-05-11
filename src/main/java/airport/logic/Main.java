package airport.logic;

import airport.DAO.GenericDAO;
import airport.DAO.Impl.*;
import airport.DAO.PassengerDAO;
import airport.DAO.PassportDAO;
import airport.entity.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {

    public static void main(String[] args) {

        GenericDAO<Aircraft> aircraftGenericDAO = new AircraftDAOImpl();
        GenericDAO<AircraftTypes> aircraftTypesGenericDAO = new AircraftTypesDAOImpl();
        GenericDAO<Airline> avialineGenericDAO = new AirlineDAOImpl();
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

//        List<Aircraft> aircraftList = aircraftGenericDAO.getAll();
        List<Airline> avialineList = avialineGenericDAO.getAll();
        List<AircraftTypes> aircraftTypesList = aircraftTypesGenericDAO.getAll();
        List<Flight> flightList = flightGenericDAO.getAll();
        List<Gate> gateList = gateGenericDAO.getAll();
        List<Passenger> passengerList = passengerGenericDAO.getAll();
        List<Routes> routeList = routeGenericDAO.getAll();
        List<Terminal> terminalList = terminalGenericDAO.getAll();
        List<Ticket> ticketList = ticketGenericDAO.getAll();
        System.out.println(passengerGenericDAO.getById(100));

//        aircraftList.forEach(System.out::println);
        aircraftTypesList.forEach(System.out::println);
        avialineList.forEach(System.out::println);
        flightList.forEach(System.out::println);
        gateList.forEach(System.out::println);
        passengerList.forEach(System.out::println);
        routeList.forEach(System.out::println);
        terminalList.forEach(System.out::println);
        ticketList.forEach(System.out::println);

        Ticket ticketById = ticketGenericDAO.getById(11).orElseThrow();
        System.out.println(ticketById);

//        Passenger newPassanger = Passenger.builder()
//                .birthdate(LocalDate.of(1990, 10, 10))
//                .name("Jack")
//                .surname("Daniels")
//                .passport("EA123456")
//                .build();
////        passengerGenericDAO.add(newPassanger);

        PassengerDAO passengerPassengerDAO = new PassengerDAOImpl();
        passengerPassengerDAO.getByAirline(avialineGenericDAO.getById(1).orElseThrow()).forEach(System.out::println);

//        BuyTicket.buyTicket(passengerGenericDAO.getById(2).orElseThrow(),flightGenericDAO.getById(1).orElseThrow());

        passengerPassengerDAO.getByIdWithTickets(2).orElseThrow().getTickets().forEach(System.out::println);

//        GenericDAO<Personal> personalGenericDAO = new PersonalDAOImpl();
//        personalGenericDAO.getAll().forEach(System.out::println);
//        System.out.println(personalGenericDAO.getAll().size());
        Passenger passenger2 = passengerPassengerDAO.getByIdWithPassports(2);
        System.out.println(passenger2);
        passenger2.getPassports().forEach(System.out::println);

        PassportDAO passportDAO = new PassportDAOImpl();
        passportDAO.getAllActive().forEach(System.out::println);
    }
}
