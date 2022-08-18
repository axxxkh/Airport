package com.flightService.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flightService.FlightApplication;
import com.flightService.dto.FlightDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest(classes = FlightApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FlightControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper mapper;

    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    private HttpEntity httpEntity;

    @Test
    public void getAllFlightsTest_ExpectFlight() {
        String uri = "http://localhost:" + port + "/flight/";

        restTemplate.getRestTemplate().setInterceptors(
                Collections.singletonList((request, body, execution) -> {
                    request.getHeaders()
                            .add("email", "alxxxkh@gmail.com");
                    return execution.execute(request, body);
                }));

        ResponseEntity<FlightDTO[]> response = restTemplate.getForEntity(uri, FlightDTO[].class);
        List<FlightDTO> flightDTOS = Arrays.asList(response.getBody());
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(6, flightDTOS.size());
    }

    @Test
    public void getAllFlightsTest_ExpectFlightXz() {
        String url = "http://localhost:" + port + "/flight/period/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("startDate", "2023-01-01")
                .queryParam("endDate", "2024-01-01");

        ResponseEntity<FlightDTO[]> responseEntity = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                FlightDTO[].class
        );

        List<FlightDTO> flightDTOS = Arrays.asList(responseEntity.getBody());
        Assertions.assertEquals(202, responseEntity.getStatusCodeValue());
        Assertions.assertEquals(2, flightDTOS.size());
    }
}
