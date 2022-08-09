package com.flightService.service;

import com.flightService.dto.FlightDTO;
import com.flightService.entity.*;
import com.flightService.repository.*;
import com.flightService.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private AirlineRepository airlineRepository;
    @Autowired
    private AircraftRepository aircraftRepository;
    @Autowired
    private GateRepository gateRepository;
    @Autowired
    private RouteRepository routeRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private FlightService flightService = new FlightServiceImpl(flightRepository, mapper);


    private final int flightNumber = 9986655;

    @BeforeEach
    void initUseCase() {

    }

    @AfterEach
    public void deleteFlight() {
        Optional<Flight> flight = flightRepository.findByFlightNumber(flightNumber);
        flight.ifPresent(f -> flightRepository.delete(f));
    }

    @Test
    void saveFlight() {
        Airline airline = airlineRepository.getReferenceById(1L);
        Aircraft aircraft = aircraftRepository.getReferenceById(1L);
        Gate gate = gateRepository.getReferenceById(1L);

        Route route = routeRepository.getReferenceById(1L);

        Flight testFlight = Flight.builder()
                .flightNumber(flightNumber)
                .airline(airline)
                .aircraft(aircraft)
                .gate(gate)
                .route(route)
                .time(LocalDateTime.of(2022, 10, 10, 12, 00, 00))
                .build();
        Flight flightDB = flightRepository.save(testFlight);
        Assertions.assertEquals(flightDB, testFlight);
    }

    @Test
    void getAll() {
        List<Flight> flights = flightRepository.findAll();
        List<FlightDTO> flights1 = flightService.getAll();

        Assertions.assertEquals(6, flights1.size());
    }
}
