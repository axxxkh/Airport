package airport;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Main {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);

        //        GenericDAO<AircraftType> aircraftTypesGenericDAO = new AircraftTypesDAOImpl();
//        GenericDAO<Airline> airlineGenericDAO = new AirlineDAOImpl();
//        FlightDAO flightGenericDAO = new FlightDAOImpl();
//        GenericDAO<Gate> gateGenericDAO = new GateDAOImpl();
//        GenericDAO<Passenger> passengerGenericDAO = new PassengerDAOImpl();
//        GenericDAO<Route> routeGenericDAO = new RoutesDAOImpl();
//        GenericDAO<Terminal> terminalGenericDAO = new TerminalDAOImpl();
//        GenericDAO<Ticket> ticketGenericDAO = new TicketDAOImpl();
//
//       GenericDAO<Aircraft> aircraftGenericDAO = new AircraftDAOImpl();
/*
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
        List<Airline> avialineList = airlineGenericDAO.getAll();
        List<AircraftType> aircraftTypesList = aircraftTypesGenericDAO.getAll();
        List<Flight> flightList = flightGenericDAO.getAll();
        List<Gate> gateList = gateGenericDAO.getAll();
        List<Passenger> passengerList = passengerGenericDAO.getAll();
        List<Route> routeList = routeGenericDAO.getAll();
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

//        Ticket ticketById = ticketGenericDAO.getById(11).orElseThrow();
//        System.out.println(ticketById);

//        Passenger newPassanger = Passenger.builder()
//                .birthdate(LocalDate.of(1990, 10, 10))
//                .name("Jack")
//                .surname("Daniels")
//                .passport("EA123456")
//                .build();
////        passengerGenericDAO.add(newPassanger);

        PassengerDAO passengerPassengerDAO = new PassengerDAOImpl();
        passengerPassengerDAO.getByAirline(airlineGenericDAO.getById(1).orElseThrow()).forEach(System.out::println);

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
        TicketRepository ticketRepository = new TicketRepository();*/
//        Airline airline = airlineGenericDAO.getById(1).orElseThrow();
//        Flight flight = Flight.builder()
//                .flightStatus(Byte.parseByte("0"))
//                .flightNumber(1234)
//                .airline(airline)
//                .craftId(airline.getAirCrafts().get(1))
//                .gateId(gateGenericDAO.getById(1).orElseThrow())
//                .route(routeGenericDAO.getById(1).orElseThrow())
//                .build();
//        flightGenericDAO.add(flight);
//        flight=flightGenericDAO.getFlightByNumber(flight.getFlightNumber());
//        TicketService ticketService = new TicketServiceImpl();
//        ticketService.generateAndWriteTicketsForFlight(flight);
    }
}
