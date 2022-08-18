package auth.controller;

import auth.AuthApplication;
import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.dto.RegisterRequest;
import auth.exceptions.ErrorDetails;
import auth.jwt.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@SpringBootTest(classes = AuthApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthControllerTest {

    @LocalServerPort
    private int port;

    private String uri;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private ObjectMapper mapper;

    private MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    private HttpEntity httpEntity;

    @Test
    public void loginTest_ExpectOk() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("alxxxkh@gmail.com")
                .password("123")
                .build();
        uri = "http://localhost:" + port + "/auth/login";

        String requestBody = mapper.writeValueAsString(request);

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<LoginRequest>(request, headers);

        ResponseEntity<AuthResponse> response = restTemplate
                .postForEntity(uri, httpEntity, AuthResponse.class);
        AuthResponse authResponse = response.getBody();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(authResponse);
        Assertions.assertNotNull(authResponse.getEmail());
        Assertions.assertNotNull(authResponse.getAccessToken());
        Assertions.assertEquals(request.getEmail(), authResponse.getEmail());
    }

    @Test
    public void loginJwtTest_ExpectOk() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("alxxxkh@gmail.com")
                .password("123")
                .build();
        uri = "http://localhost:" + port + "/auth/login";

        String requestBody = mapper.writeValueAsString(request);

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<LoginRequest>(request, headers);

        ResponseEntity<AuthResponse> response = restTemplate
                .postForEntity(uri, httpEntity, AuthResponse.class);
        AuthResponse authResponse = response.getBody();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(authResponse.getAccessToken());
        Assertions.assertTrue(jwtUtil.validateToken(authResponse.getAccessToken()));
    }

    @Test
    public void loginTest_ExpectUserNotExist() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("userNotExist@gmail.com")
                .password("123")
                .build();

        String requestBody = mapper.writeValueAsString(request);
        uri = "http://localhost:" + port + "/auth/login";

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<LoginRequest>(request, headers);

        ResponseEntity<ErrorDetails> response = restTemplate
                .postForEntity(uri, httpEntity, ErrorDetails.class);

        ErrorDetails errorDetails = response.getBody();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(errorDetails);
        Assertions.assertNotNull(errorDetails.getMessage());
        Assertions.assertEquals(String.format("User %s not found", request.getEmail()), errorDetails.getMessage());
    }

    @Test
    public void loginTest_ExpectWrongPassword() throws Exception {
        LoginRequest request = LoginRequest.builder()
                .email("alxxxkh@gmail.com")
                .password("123456")
                .build();

        String requestBody = mapper.writeValueAsString(request);
        uri = "http://localhost:" + port + "/auth/login";

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<LoginRequest>(request, headers);

        ResponseEntity<ErrorDetails> response = restTemplate
                .postForEntity(uri, httpEntity, ErrorDetails.class);

        ErrorDetails errorDetails = response.getBody();

        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        Assertions.assertNotNull(errorDetails);
        Assertions.assertNotNull(errorDetails.getMessage());
        Assertions.assertEquals(String.format("Wrong password for user with email %s", request.getEmail()), errorDetails.getMessage());
    }

    @Test
    public void registerTest_ExpectOk() throws Exception {
        RegisterRequest request = RegisterRequest.builder()
                .email("newUser@gmail.com")
                .password("123456")
                .secretQuestion("Question")
                .secretAnswer("Answer")
                .build();

        uri = "http://localhost:" + port + "/auth/register";

        String requestBody = mapper.writeValueAsString(request);

        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        httpEntity = new HttpEntity<RegisterRequest>(request, headers);

        ResponseEntity<AuthResponse> response = restTemplate
                .postForEntity(uri, httpEntity, AuthResponse.class);
        AuthResponse authResponse = response.getBody();

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertNotNull(authResponse);
        Assertions.assertNotNull(authResponse.getEmail());
        Assertions.assertNotNull(authResponse.getAccessToken());
        Assertions.assertEquals(request.getEmail(), authResponse.getEmail());
    }
}