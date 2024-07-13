package mini2Backend.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import mini2Backend.demo.DTO.AppointmentResponse;
import mini2Backend.demo.DTO.DoctorAppointmentResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.entities.Role;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.AppointmentRepository;
import mini2Backend.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AppointmentService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class AppointmentServiceTest {
    @MockBean
    private AppointmentRepository appointmentRepository;

    @Autowired
    private AppointmentService appointmentService;

    @MockBean
    private UserRepository userRepository;


    @Test
    void testBookAppointment() {
        // Arrange
        Appointment appointment = new Appointment();
        appointment.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment.setAid(1);
        appointment.setUser(new User());

        Medication medication = new Medication();
        medication.setMId(1);
        medication.setMPrescription(new ArrayList<>());
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
        medication2.setMId(1);
        medication2.setMPrescription(new ArrayList<>());
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
        medication3.setMId(1);
        medication3.setMPrescription(new ArrayList<>());
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

        Appointment appointment4 = new Appointment();
        Date adate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        appointment4.setAdate(adate);
        appointment4.setAid(1);
        appointment4.setUser(user3);
        when(appointmentRepository.save(Mockito.<Appointment>any())).thenReturn(appointment4);

        Appointment appointment5 = new Appointment();
        appointment5.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment5.setAid(1);
        appointment5.setUser(new User());

        Medication medication4 = new Medication();
        medication4.setMId(1);
        medication4.setMPrescription(new ArrayList<>());
        medication4.setUser(new User());

        User user4 = new User();
        user4.setAppointment(appointment5);
        user4.setEmail("jane.doe@example.org");
        user4.setMedicalHistory("Medical History");
        user4.setMedication(medication4);
        user4.setPassword("password");
        user4.setPhone("6625550144");
        user4.setRole(Role.USER);
        user4.setUserId(1);
        user4.setUserName("janedoe");

        Appointment appointment6 = new Appointment();
        appointment6.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment6.setAid(1);
        appointment6.setUser(user4);

        Appointment appointment7 = new Appointment();
        appointment7.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment7.setAid(1);
        appointment7.setUser(new User());

        Medication medication5 = new Medication();
        medication5.setMId(1);
        medication5.setMPrescription(new ArrayList<>());
        medication5.setUser(new User());

        User user5 = new User();
        user5.setAppointment(appointment7);
        user5.setEmail("jane.doe@example.org");
        user5.setMedicalHistory("Medical History");
        user5.setMedication(medication5);
        user5.setPassword("password");
        user5.setPhone("6625550144");
        user5.setRole(Role.USER);
        user5.setUserId(1);
        user5.setUserName("janedoe");

        Medication medication6 = new Medication();
        medication6.setMId(1);
        medication6.setMPrescription(new ArrayList<>());
        medication6.setUser(user5);

        User user6 = new User();
        user6.setAppointment(appointment6);
        user6.setEmail("jane.doe@example.org");
        user6.setMedicalHistory("Medical History");
        user6.setMedication(medication6);
        user6.setPassword("password");
        user6.setPhone("6625550144");
        user6.setRole(Role.USER);
        user6.setUserId(1);
        user6.setUserName("janedoe");
        Optional<User> ofResult = Optional.of(user6);
        when(userRepository.findByUserId(anyInt())).thenReturn(ofResult);

        User user7 = new User();
        user7.setAppointment(new Appointment());
        user7.setEmail("jane.doe@example.org");
        user7.setMedicalHistory("Medical History");
        user7.setMedication(new Medication());
        user7.setPassword("password");
        user7.setPhone("6625550144");
        user7.setRole(Role.USER);
        user7.setUserId(1);
        user7.setUserName("janedoe");

        Appointment appointment8 = new Appointment();
        appointment8.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment8.setAid(1);
        appointment8.setUser(user7);

        User user8 = new User();
        user8.setAppointment(new Appointment());
        user8.setEmail("jane.doe@example.org");
        user8.setMedicalHistory("Medical History");
        user8.setMedication(new Medication());
        user8.setPassword("password");
        user8.setPhone("6625550144");
        user8.setRole(Role.USER);
        user8.setUserId(1);
        user8.setUserName("janedoe");

        Medication medication7 = new Medication();
        medication7.setMId(1);
        medication7.setMPrescription(new ArrayList<>());
        medication7.setUser(user8);

        User user9 = new User();
        user9.setAppointment(appointment8);
        user9.setEmail("jane.doe@example.org");
        user9.setMedicalHistory("Medical History");
        user9.setMedication(medication7);
        user9.setPassword("password");
        user9.setPhone("6625550144");
        user9.setRole(Role.USER);
        user9.setUserId(1);
        user9.setUserName("janedoe");

        Appointment appointment9 = new Appointment();
        appointment9.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment9.setAid(1);
        appointment9.setUser(user9);

        // Act
        AppointmentResponse actualBookAppointmentResult = appointmentService.bookAppointment(appointment9, 1);

        // Assert
        verify(userRepository).findByUserId(eq(1));
        verify(appointmentRepository).save(isA(Appointment.class));
        assertEquals(1, actualBookAppointmentResult.getAid());
        assertSame(adate, actualBookAppointmentResult.getAdate());
    }


    @Test
    void testCancelAppointment() {
        // Arrange
        doNothing().when(appointmentRepository).deleteByAppointmentId(anyInt());

        // Act
        appointmentService.cancelAppointment(1);

        // Assert that nothing has changed
        verify(appointmentRepository).deleteByAppointmentId(eq(1));
    }


    @Test
    void testGetAllAppointments() {
        // Arrange
        when(appointmentRepository.findAll()).thenReturn(new ArrayList<>());

        // Act
        List<DoctorAppointmentResponse> actualAllAppointments = appointmentService.getAllAppointments();

        // Assert
        verify(appointmentRepository).findAll();
        assertTrue(actualAllAppointments.isEmpty());
    }


    @Test
    void testGetAllAppointments2() {
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
        medication.setMId(1);
        medication.setMPrescription(new ArrayList<>());
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

        Appointment appointment2 = new Appointment();
        Date adate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        appointment2.setAdate(adate);
        appointment2.setAid(1);
        appointment2.setUser(user3);

        ArrayList<Appointment> appointmentList = new ArrayList<>();
        appointmentList.add(appointment2);
        when(appointmentRepository.findAll()).thenReturn(appointmentList);

        // Act
        List<DoctorAppointmentResponse> actualAllAppointments = appointmentService.getAllAppointments();

        // Assert
        verify(appointmentRepository).findAll();
        assertEquals(1, actualAllAppointments.size());
        DoctorAppointmentResponse getResult = actualAllAppointments.get(0);
        assertEquals(1, getResult.getAid());
        assertEquals(1, getResult.getUserId());
        assertSame(adate, getResult.getAdate());
    }


    @Test
    void testGetAppointmentById() {
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
        medication.setMId(1);
        medication.setMPrescription(new ArrayList<>());
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

        Appointment appointment2 = new Appointment();
        Date adate = Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant());
        appointment2.setAdate(adate);
        appointment2.setAid(1);
        appointment2.setUser(user3);
        Optional<Appointment> ofResult = Optional.of(appointment2);
        when(appointmentRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        AppointmentResponse actualAppointmentById = appointmentService.getAppointmentById(1);

        // Assert
        verify(appointmentRepository).findById(eq(1));
        assertEquals(1, actualAppointmentById.getAid());
        assertSame(adate, actualAppointmentById.getAdate());
    }
}
