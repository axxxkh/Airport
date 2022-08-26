package com.flightService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightService.FlightApplication;
import com.flightService.dto.PassengerDTO;
import com.flightService.dto.TicketDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Sql({"/drop.sql", "/schema.sql"})
@Sql("/data.sql")
@Testcontainers
@DirtiesContext
@SpringBootTest(classes = FlightApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PassengerControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    private HttpEntity httpEntity;

    @Container
    public static MySQLContainer<?> mySqlDB = new MySQLContainer<>
            ("mysql:8.0.28")
            .withDatabaseName("airport")
            .withUsername("admin")
            .withPassword("admin");

    @DynamicPropertySource
    public static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", mySqlDB::getJdbcUrl);
        registry.add("spring.datasource.username", mySqlDB::getUsername);
        registry.add("spring.datasource.password", mySqlDB::getPassword);

    }

    @Test
    public void getPassengersTest() {
        String uri = "http://localhost:" + port + "/passenger/";

        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("email", "alxxxkh@gmail.com");
                    return execution.execute(request, body);
                }));

        ResponseEntity<PassengerDTO[]> response = restTemplate.getForEntity(uri, PassengerDTO[].class);
        List<PassengerDTO> passengerDTOS = Arrays.asList(response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(1, passengerDTOS.size());
    }

    @Test
    public void getTicketsTest() {
        String uri = "http://localhost:" + port + "/passenger/tickets";

        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("email", "alxxxkh@gmail.com");
                    return execution.execute(request, body);
                }));

        ResponseEntity<TicketDTO[]> response = restTemplate.getForEntity(uri, TicketDTO[].class);
        List<TicketDTO> ticketDTOS = Arrays.asList(response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(6, ticketDTOS.size());
    }

    @Test
    public void freeTicketsTest() {
        String uri = "http://localhost:" + port + "/passenger/{flightId}/tickets";

        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("email", "alxxxkh@gmail.com");
                    return execution.execute(request, body);
                }));

        ResponseEntity<TicketDTO[]> response = restTemplate.getForEntity(uri, TicketDTO[].class, 11);
        List<TicketDTO> ticketDTOS = Arrays.asList(response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(347, ticketDTOS.size());
    }
}
