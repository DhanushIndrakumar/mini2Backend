package mini2Backend.demo.controllers;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;

import mini2Backend.demo.AuthImplementation.JWTServiceImpl;
import mini2Backend.demo.DTO.AppointmentResponse;
import mini2Backend.demo.DTO.RegisterRequest;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.Role;
import mini2Backend.demo.repositories.MedicationRepository;
import mini2Backend.demo.repositories.UserRepository;
import mini2Backend.demo.service.AppointmentService;
import mini2Backend.demo.service.MedicationService;
import mini2Backend.demo.service.UserCommonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {UserController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserControllerTest {
    @MockBean
    private AppointmentService appointmentService;

    @MockBean
    private MedicationService medicationService;

    @MockBean
    private UserCommonService userCommonService;

    @Autowired
    private UserController userController;


    @Test
    void testGetUser() throws Exception {
        // Arrange
        when(userCommonService.getUser(anyInt())).thenReturn(
                new RegisterResponse(1, "janedoe", "jane.doe@example.org", "6625550144", "Medical History", Role.USER));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/getUser/{userId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
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
    void testUpdateUser() throws Exception {
        // Arrange
        when(userCommonService.updateUser(Mockito.<RegisterRequest>any(), anyInt())).thenReturn(
                new RegisterResponse(1, "janedoe", "jane.doe@example.org", "6625550144", "Medical History", Role.USER));

        RegisterRequest registerRequest = new RegisterRequest();
        registerRequest.setEmail("jane.doe@example.org");
        registerRequest.setMedicalHistory("Medical History");
        registerRequest.setPassword("password");
        registerRequest.setPhone("6625550144");
        registerRequest.setUserName("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(registerRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/userUpdate/{userId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
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
    void testBookAppointment() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange
        ArrayList<AuthenticationProvider> providers = new ArrayList<>();
        providers.add(new RunAsImplAuthenticationProvider());
        ProviderManager authenticationManager = new ProviderManager(providers);
        UserRepository userRepository = mock(UserRepository.class);
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        UserCommonService userCommonService = new UserCommonService(userRepository, passwordEncoder, authenticationManager,
                new JWTServiceImpl());

        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointmentResponse.setAid(1);
        AppointmentService appointmentService = mock(AppointmentService.class);
        when(appointmentService.bookAppointment(Mockito.<Appointment>any(), anyInt())).thenReturn(appointmentResponse);

        // Act
        AppointmentResponse actualBookAppointmentResult = (new UserController(userCommonService, appointmentService,
                new MedicationService(mock(MedicationRepository.class), mock(UserRepository.class))))
                .bookAppointment(mock(Appointment.class), 1);

        // Assert
        verify(appointmentService).bookAppointment(isA(Appointment.class), eq(1));
        assertSame(appointmentResponse, actualBookAppointmentResult);
    }


    @Test
    void testGetAppointmentById() throws Exception {
        // Arrange
        AppointmentResponse appointmentResponse = new AppointmentResponse();
        appointmentResponse.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointmentResponse.setAid(1);
        when(appointmentService.getAppointmentById(anyInt())).thenReturn(appointmentResponse);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/user/getAppointment/{appointmentId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"aid\":1,\"adate\":0}"));
    }


    @Test
    void testGetMedicineByUserId() throws Exception {
        // Arrange
        Mockito.<ResponseEntity<?>>when(medicationService.getMedicineByUserId(anyInt())).thenReturn(null);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/user/getMedication/{userId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }




    @Test
    void testDeleteUser() throws Exception {
        // Arrange
        doNothing().when(userCommonService).removeUser(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/user/deleteUser/{userId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(userController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("user successfully deleted"));
    }



}
