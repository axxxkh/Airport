package com.flightService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightService.FlightApplication;
import com.flightService.dto.TicketDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Sql({"/drop.sql", "/schema.sql"})
@Sql("/data.sql")
@SpringBootTest(classes = FlightApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TicketControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    private HttpEntity httpEntity;

    @Test
    public void getAllAvailableTest() {
        String uri = "http://localhost:" + port + "/ticket/{flightNumber}/";

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

    @Test
    public void buyTicketTest() throws JsonProcessingException {
        String uri = "http://localhost:" + port + "/ticket/";

        TicketDTO requestDTO = TicketDTO.builder()
                .flightNumber(15)
                .passengerId(2L)
                .seat(111)
                .build();

        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("email", "alxxxkh@gmail.com");
                    return execution.execute(request, body);
                }));

//        String requestBody = mapper.writeValueAsString(requestDTO);

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<TicketDTO>(requestDTO, headers);

        ResponseEntity<TicketDTO> response = restTemplate.postForEntity(uri, httpEntity, TicketDTO.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(111, response.getBody().getSeat());
    }
}
