package com.flightService.service;

import com.flightService.dto.PassengerDTO;
import com.flightService.repository.PassengerRepository;
import com.flightService.service.impl.PassengerServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class PassengerServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PassengerRepository passengerRepository;
    @Autowired
    private ModelMapper mapper;

    @Autowired
    private final PassengerService passengerService = new PassengerServiceImpl(passengerRepository, mapper);

    @Test
    public void getAllByEmailTest() {
        List<PassengerDTO> passengerDTOS = passengerService.getAllByEmail("alxxxkh@gmail.com");
        Assertions.assertEquals(1, passengerDTOS.size());
    }
}
