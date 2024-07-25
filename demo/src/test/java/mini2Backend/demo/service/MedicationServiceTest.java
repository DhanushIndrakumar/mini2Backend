package mini2Backend.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import mini2Backend.demo.DTO.MedicationRequest;
import mini2Backend.demo.DTO.MedicationResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.entities.Role;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.MedicationRepository;
import mini2Backend.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MedicationService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class MedicationServiceTest {
    @MockBean
    private MedicationRepository medicationRepository;

    @Autowired
    private MedicationService medicationService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test:
     * {@link MedicationService#prescribeMedicine(MedicationRequest, int)}
     */
    @Test
    void testPrescribeMedicine() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment.setAid(1);
        appointment.setUser(new User());

        Medication medication = new Medication();
        medication.setMid(1);
        medication.setMprescription(new ArrayList<>());
        medication.setUser(new User());

        User user = new User();
        user.setAppointment(appointment);
        user.setEmail("jane.doe@example.org");
        user.setMedicalHistory("Medical History");
        user.setMedication(medication);
        user.setPassword("password");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        user.setUserId(1);
        user.setUserName("janedoe");

        Appointment appointment2 = new Appointment();
        appointment2.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment2.setAid(1);
        appointment2.setUser(user);

        Appointment appointment3 = new Appointment();
        appointment3.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment3.setAid(1);
        appointment3.setUser(new User());

        Medication medication2 = new Medication();
        medication2.setMid(1);
        medication2.setMprescription(new ArrayList<>());
        medication2.setUser(new User());

        User user2 = new User();
        user2.setAppointment(appointment3);
        user2.setEmail("jane.doe@example.org");
        user2.setMedicalHistory("Medical History");
        user2.setMedication(medication2);
        user2.setPassword("password");
        user2.setPhone("6625550144");
        user2.setRole(Role.USER);
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Medication medication3 = new Medication();
        medication3.setMid(1);
        medication3.setMprescription(new ArrayList<>());
        medication3.setUser(user2);

        User user3 = new User();
        user3.setAppointment(appointment2);
        user3.setEmail("jane.doe@example.org");
        user3.setMedicalHistory("Medical History");
        user3.setMedication(medication3);
        user3.setPassword("password");
        user3.setPhone("6625550144");
        user3.setRole(Role.USER);
        user3.setUserId(1);
        user3.setUserName("janedoe");

        Medication medication4 = new Medication();
        medication4.setMid(1);
        ArrayList<String> mprescription = new ArrayList<>();
        medication4.setMprescription(mprescription);
        medication4.setUser(user3);
        when(medicationRepository.save(Mockito.<Medication>any())).thenReturn(medication4);

        Appointment appointment4 = new Appointment();
        appointment4.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment4.setAid(1);
        appointment4.setUser(new User());

        Medication medication5 = new Medication();
        medication5.setMid(1);
        medication5.setMprescription(new ArrayList<>());
        medication5.setUser(new User());

        User user4 = new User();
        user4.setAppointment(appointment4);
        user4.setEmail("jane.doe@example.org");
        user4.setMedicalHistory("Medical History");
        user4.setMedication(medication5);
        user4.setPassword("password");
        user4.setPhone("6625550144");
        user4.setRole(Role.USER);
        user4.setUserId(1);
        user4.setUserName("janedoe");

        Appointment appointment5 = new Appointment();
        appointment5.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment5.setAid(1);
        appointment5.setUser(user4);

        Appointment appointment6 = new Appointment();
        appointment6.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment6.setAid(1);
        appointment6.setUser(new User());

        Medication medication6 = new Medication();
        medication6.setMid(1);
        medication6.setMprescription(new ArrayList<>());
        medication6.setUser(new User());

        User user5 = new User();
        user5.setAppointment(appointment6);
        user5.setEmail("jane.doe@example.org");
        user5.setMedicalHistory("Medical History");
        user5.setMedication(medication6);
        user5.setPassword("password");
        user5.setPhone("6625550144");
        user5.setRole(Role.USER);
        user5.setUserId(1);
        user5.setUserName("janedoe");

        Medication medication7 = new Medication();
        medication7.setMid(1);
        medication7.setMprescription(new ArrayList<>());
        medication7.setUser(user5);

        User user6 = new User();
        user6.setAppointment(appointment5);
        user6.setEmail("jane.doe@example.org");
        user6.setMedicalHistory("Medical History");
        user6.setMedication(medication7);
        user6.setPassword("password");
        user6.setPhone("6625550144");
        user6.setRole(Role.USER);
        user6.setUserId(1);
        user6.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user6);
        when(userRepository.findByUserId(anyInt())).thenReturn(ofResult);

        MedicationRequest medicationRequest = new MedicationRequest();
        medicationRequest.setMprescription(new ArrayList<>());

        // Act
        MedicationResponse actualPrescribeMedicineResult = medicationService.prescribeMedicine(medicationRequest, 1);

        // Assert
        verify(userRepository).findByUserId(eq(1));
        verify(medicationRepository).save(isA(Medication.class));
        assertEquals(1, actualPrescribeMedicineResult.getMid());
        List<String> mprescription2 = actualPrescribeMedicineResult.getMprescription();
        assertTrue(mprescription2.isEmpty());
        assertSame(mprescription, mprescription2);
    }

    /**
     * Method under test:
     * {@link MedicationService#removeMedicineFromList(String, int)}
     */
    @Test
    void testRemoveMedicineFromList() {
        // Arrange
        User user = new User();
        user.setAppointment(new Appointment());
        user.setEmail("jane.doe@example.org");
        user.setMedicalHistory("Medical History");
        user.setMedication(new Medication());
        user.setPassword("password");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        user.setUserId(1);
        user.setUserName("janedoe");

        Appointment appointment = new Appointment();
        appointment.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment.setAid(1);
        appointment.setUser(user);

        User user2 = new User();
        user2.setAppointment(new Appointment());
        user2.setEmail("jane.doe@example.org");
        user2.setMedicalHistory("Medical History");
        user2.setMedication(new Medication());
        user2.setPassword("password");
        user2.setPhone("6625550144");
        user2.setRole(Role.USER);
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Medication medication = new Medication();
        medication.setMid(1);
        medication.setMprescription(new ArrayList<>());
        medication.setUser(user2);

        User user3 = new User();
        user3.setAppointment(appointment);
        user3.setEmail("jane.doe@example.org");
        user3.setMedicalHistory("Medical History");
        user3.setMedication(medication);
        user3.setPassword("password");
        user3.setPhone("6625550144");
        user3.setRole(Role.USER);
        user3.setUserId(1);
        user3.setUserName("janedoe");

        Medication medication2 = new Medication();
        medication2.setMid(1);
        ArrayList<String> mprescription = new ArrayList<>();
        medication2.setMprescription(mprescription);
        medication2.setUser(user3);
        Optional<Medication> ofResult = Optional.of(medication2);
        when(medicationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<String> actualRemoveMedicineFromListResult = medicationService.removeMedicineFromList("Medicine", 1);

        // Assert
        verify(medicationRepository).findById(eq(1));
        assertTrue(actualRemoveMedicineFromListResult.isEmpty());
        assertSame(mprescription, actualRemoveMedicineFromListResult);
    }

    /**
     * Method under test:
     * {@link MedicationService#removeMedicineFromList(String, int)}
     */
    @Test
    void testRemoveMedicineFromList2() {
        // Arrange
        ArrayList<String> mprescription = new ArrayList<>();
        mprescription.add("foo");

        User user = new User();
        user.setAppointment(new Appointment());
        user.setEmail("jane.doe@example.org");
        user.setMedicalHistory("Medical History");
        user.setMedication(new Medication());
        user.setPassword("password");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        user.setUserId(1);
        user.setUserName("janedoe");

        Appointment appointment = new Appointment();
        appointment.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment.setAid(1);
        appointment.setUser(user);

        User user2 = new User();
        user2.setAppointment(new Appointment());
        user2.setEmail("jane.doe@example.org");
        user2.setMedicalHistory("Medical History");
        user2.setMedication(new Medication());
        user2.setPassword("password");
        user2.setPhone("6625550144");
        user2.setRole(Role.USER);
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Medication medication = new Medication();
        medication.setMid(1);
        medication.setMprescription(new ArrayList<>());
        medication.setUser(user2);

        User user3 = new User();
        user3.setAppointment(appointment);
        user3.setEmail("jane.doe@example.org");
        user3.setMedicalHistory("Medical History");
        user3.setMedication(medication);
        user3.setPassword("password");
        user3.setPhone("6625550144");
        user3.setRole(Role.USER);
        user3.setUserId(1);
        user3.setUserName("janedoe");

        Medication medication2 = new Medication();
        medication2.setMid(1);
        medication2.setMprescription(mprescription);
        medication2.setUser(user3);
        Optional<Medication> ofResult = Optional.of(medication2);
        when(medicationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        List<String> actualRemoveMedicineFromListResult = medicationService.removeMedicineFromList("Medicine", 1);

        // Assert
        verify(medicationRepository).findById(eq(1));
        assertEquals(1, actualRemoveMedicineFromListResult.size());
        assertEquals("foo", actualRemoveMedicineFromListResult.get(0));
        assertSame(mprescription, actualRemoveMedicineFromListResult);
    }

    /**
     * Method under test:
     * {@link MedicationService#updateMedicineList(MedicationRequest, int)}
     */
    @Test
    void testUpdateMedicineList() {
        // Arrange
        User user = new User();
        user.setAppointment(new Appointment());
        user.setEmail("jane.doe@example.org");
        user.setMedicalHistory("Medical History");
        user.setMedication(new Medication());
        user.setPassword("password");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        user.setUserId(1);
        user.setUserName("janedoe");

        Appointment appointment = new Appointment();
        appointment.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment.setAid(1);
        appointment.setUser(user);

        User user2 = new User();
        user2.setAppointment(new Appointment());
        user2.setEmail("jane.doe@example.org");
        user2.setMedicalHistory("Medical History");
        user2.setMedication(new Medication());
        user2.setPassword("password");
        user2.setPhone("6625550144");
        user2.setRole(Role.USER);
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Medication medication = new Medication();
        medication.setMid(1);
        medication.setMprescription(new ArrayList<>());
        medication.setUser(user2);

        User user3 = new User();
        user3.setAppointment(appointment);
        user3.setEmail("jane.doe@example.org");
        user3.setMedicalHistory("Medical History");
        user3.setMedication(medication);
        user3.setPassword("password");
        user3.setPhone("6625550144");
        user3.setRole(Role.USER);
        user3.setUserId(1);
        user3.setUserName("janedoe");

        Medication medication2 = new Medication();
        medication2.setMid(1);
        medication2.setMprescription(new ArrayList<>());
        medication2.setUser(user3);
        Optional<Medication> ofResult = Optional.of(medication2);
        when(medicationRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        MedicationRequest medicationRequest = new MedicationRequest();
        ArrayList<String> mprescription = new ArrayList<>();
        medicationRequest.setMprescription(mprescription);

        // Act
        List<String> actualUpdateMedicineListResult = medicationService.updateMedicineList(medicationRequest, 1);

        // Assert
        verify(medicationRepository).findById(eq(1));
        assertSame(mprescription, medicationRequest.getMprescription());
        assertSame(mprescription, actualUpdateMedicineListResult);
    }

    /**
     * Method under test:
     * {@link MedicationService#updateMedicineList1(MedicationRequest, int)}
     */
    @Test
    void testUpdateMedicineList1() {
        // Arrange
        User user = new User();
        user.setAppointment(new Appointment());
        user.setEmail("jane.doe@example.org");
        user.setMedicalHistory("Medical History");
        user.setMedication(new Medication());
        user.setPassword("password");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        user.setUserId(1);
        user.setUserName("janedoe");

        Appointment appointment = new Appointment();
        appointment.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment.setAid(1);
        appointment.setUser(user);

        User user2 = new User();
        user2.setAppointment(new Appointment());
        user2.setEmail("jane.doe@example.org");
        user2.setMedicalHistory("Medical History");
        user2.setMedication(new Medication());
        user2.setPassword("password");
        user2.setPhone("6625550144");
        user2.setRole(Role.USER);
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Medication medication = new Medication();
        medication.setMid(1);
        medication.setMprescription(new ArrayList<>());
        medication.setUser(user2);

        User user3 = new User();
        user3.setAppointment(appointment);
        user3.setEmail("jane.doe@example.org");
        user3.setMedicalHistory("Medical History");
        user3.setMedication(medication);
        user3.setPassword("password");
        user3.setPhone("6625550144");
        user3.setRole(Role.USER);
        user3.setUserId(1);
        user3.setUserName("janedoe");

        Medication medication2 = new Medication();
        medication2.setMid(1);
        medication2.setMprescription(new ArrayList<>());
        medication2.setUser(user3);
        Optional<Medication> ofResult = Optional.of(medication2);

        Appointment appointment2 = new Appointment();
        appointment2.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment2.setAid(1);
        appointment2.setUser(new User());

        Medication medication3 = new Medication();
        medication3.setMid(1);
        medication3.setMprescription(new ArrayList<>());
        medication3.setUser(new User());

        User user4 = new User();
        user4.setAppointment(appointment2);
        user4.setEmail("jane.doe@example.org");
        user4.setMedicalHistory("Medical History");
        user4.setMedication(medication3);
        user4.setPassword("password");
        user4.setPhone("6625550144");
        user4.setRole(Role.USER);
        user4.setUserId(1);
        user4.setUserName("janedoe");

        Appointment appointment3 = new Appointment();
        appointment3.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment3.setAid(1);
        appointment3.setUser(user4);

        Appointment appointment4 = new Appointment();
        appointment4.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment4.setAid(1);
        appointment4.setUser(new User());

        Medication medication4 = new Medication();
        medication4.setMid(1);
        medication4.setMprescription(new ArrayList<>());
        medication4.setUser(new User());

        User user5 = new User();
        user5.setAppointment(appointment4);
        user5.setEmail("jane.doe@example.org");
        user5.setMedicalHistory("Medical History");
        user5.setMedication(medication4);
        user5.setPassword("password");
        user5.setPhone("6625550144");
        user5.setRole(Role.USER);
        user5.setUserId(1);
        user5.setUserName("janedoe");

        Medication medication5 = new Medication();
        medication5.setMid(1);
        medication5.setMprescription(new ArrayList<>());
        medication5.setUser(user5);

        User user6 = new User();
        user6.setAppointment(appointment3);
        user6.setEmail("jane.doe@example.org");
        user6.setMedicalHistory("Medical History");
        user6.setMedication(medication5);
        user6.setPassword("password");
        user6.setPhone("6625550144");
        user6.setRole(Role.USER);
        user6.setUserId(1);
        user6.setUserName("janedoe");

        Medication medication6 = new Medication();
        medication6.setMid(1);
        medication6.setMprescription(new ArrayList<>());
        medication6.setUser(user6);
        when(medicationRepository.save(Mockito.<Medication>any())).thenReturn(medication6);
        when(medicationRepository.findByUserId(anyInt())).thenReturn(ofResult);

        MedicationRequest medicationRequest = new MedicationRequest();
        ArrayList<String> mprescription = new ArrayList<>();
        medicationRequest.setMprescription(mprescription);

        // Act
        List<String> actualUpdateMedicineList1Result = medicationService.updateMedicineList1(medicationRequest, 1);

        // Assert
        verify(medicationRepository).findByUserId(eq(1));
        verify(medicationRepository).save(isA(Medication.class));
        assertSame(mprescription, medicationRequest.getMprescription());
        assertSame(mprescription, actualUpdateMedicineList1Result);
    }

    /**
     * Method under test: {@link MedicationService#getMedicineByUserId(int)}
     */
    @Test
    void testGetMedicineByUserId() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment.setAid(1);
        appointment.setUser(new User());

        Medication medication = new Medication();
        medication.setMid(1);
        medication.setMprescription(new ArrayList<>());
        medication.setUser(new User());

        User user = new User();
        user.setAppointment(appointment);
        user.setEmail("jane.doe@example.org");
        user.setMedicalHistory("Medical History");
        user.setMedication(medication);
        user.setPassword("password");
        user.setPhone("6625550144");
        user.setRole(Role.USER);
        user.setUserId(1);
        user.setUserName("janedoe");

        Appointment appointment2 = new Appointment();
        appointment2.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment2.setAid(1);
        appointment2.setUser(user);

        Appointment appointment3 = new Appointment();
        appointment3.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment3.setAid(1);
        appointment3.setUser(new User());

        Medication medication2 = new Medication();
        medication2.setMid(1);
        medication2.setMprescription(new ArrayList<>());
        medication2.setUser(new User());

        User user2 = new User();
        user2.setAppointment(appointment3);
        user2.setEmail("jane.doe@example.org");
        user2.setMedicalHistory("Medical History");
        user2.setMedication(medication2);
        user2.setPassword("password");
        user2.setPhone("6625550144");
        user2.setRole(Role.USER);
        user2.setUserId(1);
        user2.setUserName("janedoe");

        Medication medication3 = new Medication();
        medication3.setMid(1);
        medication3.setMprescription(new ArrayList<>());
        medication3.setUser(user2);

        User user3 = new User();
        user3.setAppointment(appointment2);
        user3.setEmail("jane.doe@example.org");
        user3.setMedicalHistory("Medical History");
        user3.setMedication(medication3);
        user3.setPassword("password");
        user3.setPhone("6625550144");
        user3.setRole(Role.USER);
        user3.setUserId(1);
        user3.setUserName("janedoe");

        Medication medication4 = new Medication();
        medication4.setMid(1);
        medication4.setMprescription(new ArrayList<>());
        medication4.setUser(user3);
        when(medicationRepository.findByUser(Mockito.<User>any())).thenReturn(medication4);

        Appointment appointment4 = new Appointment();
        appointment4.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment4.setAid(1);
        appointment4.setUser(new User());

        Medication medication5 = new Medication();
        medication5.setMid(1);
        medication5.setMprescription(new ArrayList<>());
        medication5.setUser(new User());

        User user4 = new User();
        user4.setAppointment(appointment4);
        user4.setEmail("jane.doe@example.org");
        user4.setMedicalHistory("Medical History");
        user4.setMedication(medication5);
        user4.setPassword("password");
        user4.setPhone("6625550144");
        user4.setRole(Role.USER);
        user4.setUserId(1);
        user4.setUserName("janedoe");

        Appointment appointment5 = new Appointment();
        appointment5.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment5.setAid(1);
        appointment5.setUser(user4);

        Appointment appointment6 = new Appointment();
        appointment6.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment6.setAid(1);
        appointment6.setUser(new User());

        Medication medication6 = new Medication();
        medication6.setMid(1);
        medication6.setMprescription(new ArrayList<>());
        medication6.setUser(new User());

        User user5 = new User();
        user5.setAppointment(appointment6);
        user5.setEmail("jane.doe@example.org");
        user5.setMedicalHistory("Medical History");
        user5.setMedication(medication6);
        user5.setPassword("password");
        user5.setPhone("6625550144");
        user5.setRole(Role.USER);
        user5.setUserId(1);
        user5.setUserName("janedoe");

        Medication medication7 = new Medication();
        medication7.setMid(1);
        medication7.setMprescription(new ArrayList<>());
        medication7.setUser(user5);

        User user6 = new User();
        user6.setAppointment(appointment5);
        user6.setEmail("jane.doe@example.org");
        user6.setMedicalHistory("Medical History");
        user6.setMedication(medication7);
        user6.setPassword("password");
        user6.setPhone("6625550144");
        user6.setRole(Role.USER);
        user6.setUserId(1);
        user6.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user6);
        when(userRepository.findByUserId(anyInt())).thenReturn(ofResult);

        // Act
        ResponseEntity<?> actualMedicineByUserId = medicationService.getMedicineByUserId(1);

        // Assert
        verify(medicationRepository).findByUser(isA(User.class));
        verify(userRepository).findByUserId(eq(1));
        HttpStatusCode statusCode = actualMedicineByUserId.getStatusCode();
        assertTrue(statusCode instanceof HttpStatus);
        assertEquals(200, actualMedicineByUserId.getStatusCodeValue());
        assertEquals(HttpStatus.OK, statusCode);
        assertTrue(actualMedicineByUserId.hasBody());
        assertTrue(actualMedicineByUserId.getHeaders().isEmpty());
        assertSame(medication4, actualMedicineByUserId.getBody());
    }
}
