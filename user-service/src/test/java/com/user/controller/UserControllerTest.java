package com.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.user.UserApplication;
import com.user.entity.Role;
import com.user.entity.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Sql({"/drop.sql", "/schema.sql"})
@Sql("/data.sql")
@SpringBootTest(classes = UserApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
@DirtiesContext
public class UserControllerTest {
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
        registry.add("spring.datasource.url",mySqlDB::getJdbcUrl);
        registry.add("spring.datasource.username", mySqlDB::getUsername);
        registry.add("spring.datasource.password", mySqlDB::getPassword);

    }

    @Test
    public void registerUserTest_ExpectOk() throws JsonProcessingException {
        String url = "http://localhost:" + port + "/user/register/";
        User user = User.builder()
                .email("test@email.com")
                .password("test")
                .secretQuestion("Test question")
                .secretAnswer("Test answer")
                .role(Role.builder().role("PASSENGER").build())
                .build();

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<User>(user, headers);

        ResponseEntity<User> response = restTemplate.postForEntity(url, httpEntity, User.class);
        Assertions.assertEquals(200, response.getStatusCodeValue());
        Assertions.assertEquals(user.getEmail(), response.getBody().getEmail());
    }

    @Test
    public void registerUserTest_ExpectAlreadyExist() throws JsonProcessingException {
        String url = "http://localhost:" + port + "/user/register/";
        User user = User.builder()
                .email("alxxxkh@gmail.com")
                .password("test")
                .secretQuestion("Test question")
                .secretAnswer("Test answer")
                .role(Role.builder().role("PASSENGER").build())
                .build();

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<User>(user, headers);

        ResponseEntity<User> response = restTemplate.postForEntity(url, httpEntity, User.class);
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void getByEmailTest_ExpectOk() {
        String url = "http://localhost:" + port + "/user/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("email", "alxxxkh@gmail.com");

        ResponseEntity<User> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                User.class
        );
        User user = response.getBody();
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals("alxxxkh@gmail.com", user.getEmail());
    }

    @Test
    public void getByEmail_ExpectNotFound() {
        String url = "http://localhost:" + port + "/user/";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity requestEntity = new HttpEntity<>(headers);

        UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("email", "testNotFound@email.com");

        ResponseEntity<User> response = restTemplate.exchange(
                uriBuilder.toUriString(),
                HttpMethod.GET,
                requestEntity,
                User.class
        );
        System.out.println(response);
        User user = response.getBody();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }
}
