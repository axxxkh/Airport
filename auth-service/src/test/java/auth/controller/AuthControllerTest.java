package auth.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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