package airport.utils;

import airport.DAO.TicketDAO;
import airport.DAO.impl.TicketDAOImpl;
import airport.repository.AircraftRepository;
import airport.repository.TicketRepository;
import airport.repository.impl.AircraftRepositoryImpl;
import airport.repository.impl.TicketRepositoryImpl;
import airport.service.PassengerService;
import airport.service.PassportService;
import airport.service.TicketService;
import airport.service.impl.PassengerServiceImpl;
import airport.service.impl.PassportServiceImpl;
import airport.service.impl.TicketServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public PassengerService passengerService() {
        return new PassengerServiceImpl();
    }

    @Bean
    public PassportService passportService() {
        return new PassportServiceImpl();
    }

    @Bean
    public TicketService ticketService() {
        return new TicketServiceImpl();
    }

    @Bean
    TicketRepository ticketRepository() {
        return new TicketRepositoryImpl(new TicketDAOImpl());
    }

    @Bean
    public AircraftRepository aircraftRepository() {
        return new AircraftRepositoryImpl();
    }

    @Bean
    public TicketDAO ticketDAO() {
        return new TicketDAOImpl();
    }

}
