package mini2Backend.demo.controllers;

import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import mini2Backend.demo.DTO.MedicationRequest;
import mini2Backend.demo.DTO.MedicationResponse;
import mini2Backend.demo.service.AppointmentService;
import mini2Backend.demo.service.MedicationService;
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

@ContextConfiguration(classes = {DoctorController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DoctorControllerTest {
    @MockBean
    private AppointmentService appointmentService;

    @Autowired
    private DoctorController doctorController;

    @MockBean
    private MedicationService medicationService;

    @MockBean
    private UserCommonService userCommonService;


    @Test
    void testDeleteAppointmentById() throws Exception {
        // Arrange
        doNothing().when(appointmentService).cancelAppointment(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/doctor/deleteAppointment/{appointmentId}", 1);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("appointment successfully deleted"));
    }


    @Test
    void testGetAllUsers() throws Exception {
        // Arrange
        when(userCommonService.getAllUsers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctor/getAllUsers");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testRemoveMedicineFromList() throws Exception {
        // Arrange
        when(medicationService.removeMedicineFromList(Mockito.<String>any(), anyInt())).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder contentTypeResult = MockMvcRequestBuilders
                .get("/api/doctor/removeMedicineFromList/{mId}", 1)
                .contentType(MediaType.APPLICATION_JSON);
        MockHttpServletRequestBuilder requestBuilder = contentTypeResult
                .content((new ObjectMapper()).writeValueAsString("foo"));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }


    @Test
    void testDeleteAppointmentById2() throws Exception {
        // Arrange
        doNothing().when(appointmentService).cancelAppointment(anyInt());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/doctor/deleteAppointment/{appointmentId}", 1);
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("appointment successfully deleted"));
    }


    @Test
    void testEditMedicineList() throws Exception {
        // Arrange
        when(medicationService.updateMedicineList(Mockito.<MedicationRequest>any(), anyInt()))
                .thenReturn(new ArrayList<>());

        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setMPrescription(new ArrayList<>());
        String content = (new ObjectMapper()).writeValueAsString(medicationRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctor/editMedicineList/{mId}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    void testGetAppointments() throws Exception {
        // Arrange
        when(appointmentService.getAllAppointments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/doctor/getAppointments");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(doctorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
