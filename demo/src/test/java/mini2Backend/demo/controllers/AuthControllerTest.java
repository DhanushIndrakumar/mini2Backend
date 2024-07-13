package mini2Backend.demo.controllers;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;
import mini2Backend.demo.DTO.LoginRequest;
import mini2Backend.demo.DTO.LoginResponse;
import mini2Backend.demo.DTO.RegisterRequest;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.entities.Role;
import mini2Backend.demo.service.UserCommonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private UserCommonService userCommonService;


    @Test
    void testRegister() throws Exception {
        // Arrange
        when(userCommonService.register(Mockito.<RegisterRequest>any())).thenReturn(
                new RegisterResponse(1, "janedoe", "jane.doe@example.org", "6625550144", "Medical History", Role.USER));

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("jane.doe@example.org");
        registerRequest.setMedicalHistory("Medical History");
        registerRequest.setPassword("password");
        registerRequest.setPhone("6625550144");
        registerRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"userId\":1,\"userName\":\"janedoe\",\"email\":\"jane.doe@example.org\",\"phone\":\"6625550144\",\"medicalHistory"
                                        + "\":\"Medical History\",\"role\":\"USER\"}"));
    }


    @Test
    void testLogin() throws Exception {
        // Arrange
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken("ABC123");
        when(userCommonService.login(Mockito.<LoginRequest>any())).thenReturn(loginResponse);

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setEmail("jane.doe@example.org");
        loginRequest.setPassword("password");
        String content = (new ObjectMapper()).writeValueAsString(loginRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"token\":\"ABC123\"}"));
    }
}
