package auth.controller;

import auth.dto.AuthResponse;
import auth.dto.LoginRequest;
import auth.service.AuthService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {
//
//    @MockBean
//    AuthService authService;
//
//    @Autowired
//    MockMvc mockMvc;
//
//
//    @Test
//    public void login() throws Exception {
//
//
//        String uri = "/auth/login";
//
//
//        LoginRequest request = LoginRequest.builder()
//                .email("alxxxkh@gmail.com")
//                .password("123")
//                .build();
//
//        Mockito mockito = Mockito.when(authService.login(request)).thenReturn(AuthResponse
//        )
//
//
//        String inputJson = mapper.writeValueAsString(request);
//        MvcResult mvcResult = mvc
//                .perform(MockMvcRequestBuilders.post(uri)
//                        .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson))
//                .andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getHeader("Autorization");
//
//        AuthResponse response = mapper.readValue(content, AuthResponse.class);
//        assertEquals(response.getEmail(), "alxxxkh@gmail.com");
//    }


}