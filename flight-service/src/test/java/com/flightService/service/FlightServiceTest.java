package com.flightService.service;

import com.flightService.dto.FlightDTO;
import com.flightService.entity.Flight;
import com.flightService.repository.*;
import com.flightService.service.impl.FlightServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Transactional
public class FlightServiceTest {

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

    @AfterEach
    public void deleteFlight() {
        Optional<Flight> flight = flightRepository.findByFlightNumber(flightNumber);
        flight.ifPresent(f -> flightRepository.delete(f));
    }

    @Test
    public void getAll() {
        List<FlightDTO> flights = flightService.getAll();
        Assertions.assertEquals(6, flights.size());
    }

    @Test
    public void getByPeriod() {
        List<FlightDTO> flightList = flightService.getFlightsByPeriod(LocalDate.of(2023, 01, 01), LocalDate.of(2024, 01, 01));
        Assertions.assertEquals(2, flightList.size());
    }
}
