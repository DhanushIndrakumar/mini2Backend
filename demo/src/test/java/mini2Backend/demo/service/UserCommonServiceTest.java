package mini2Backend.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

import mini2Backend.demo.DTO.RegisterRequest;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.entities.Role;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserCommonService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class UserCommonServiceTest {
    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private JWTService jWTService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserCommonService userCommonService;

    @MockBean
    private UserRepository userRepository;

    /**
     * Method under test: {@link UserCommonService#updateUser(RegisterRequest, int)}
     */
    @Test
    void testUpdateUser() {
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
        Optional<User> ofResult = Optional.of(user3);

        User user4 = new User();
        user4.setAppointment(new Appointment());
        user4.setEmail("jane.doe@example.org");
        user4.setMedicalHistory("Medical History");
        user4.setMedication(new Medication());
        user4.setPassword("password");
        user4.setPhone("6625550144");
        user4.setRole(Role.USER);
        user4.setUserId(1);
        user4.setUserName("janedoe");

        Appointment appointment4 = new Appointment();
        appointment4.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment4.setAid(1);
        appointment4.setUser(user4);

        User user5 = new User();
        user5.setAppointment(new Appointment());
        user5.setEmail("jane.doe@example.org");
        user5.setMedicalHistory("Medical History");
        user5.setMedication(new Medication());
        user5.setPassword("password");
        user5.setPhone("6625550144");
        user5.setRole(Role.USER);
        user5.setUserId(1);
        user5.setUserName("janedoe");

        Medication medication4 = new Medication();
        medication4.setMid(1);
        medication4.setMprescription(new ArrayList<>());
        medication4.setUser(user5);

        User user6 = new User();
        user6.setAppointment(appointment4);
        user6.setEmail("jane.doe@example.org");
        user6.setMedicalHistory("Medical History");
        user6.setMedication(medication4);
        user6.setPassword("password");
        user6.setPhone("6625550144");
        user6.setRole(Role.USER);
        user6.setUserId(1);
        user6.setUserName("janedoe");

        Appointment appointment5 = new Appointment();
        appointment5.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment5.setAid(1);
        appointment5.setUser(user6);

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

        Appointment appointment6 = new Appointment();
        appointment6.setAdate(Date.from(LocalDate.of(1970, 1, 1).atStartOfDay().atZone(ZoneOffset.UTC).toInstant()));
        appointment6.setAid(1);
        appointment6.setUser(user7);

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

        Medication medication5 = new Medication();
        medication5.setMid(1);
        medication5.setMprescription(new ArrayList<>());
        medication5.setUser(user8);

        User user9 = new User();
        user9.setAppointment(appointment6);
        user9.setEmail("jane.doe@example.org");
        user9.setMedicalHistory("Medical History");
        user9.setMedication(medication5);
        user9.setPassword("password");
        user9.setPhone("6625550144");
        user9.setRole(Role.USER);
        user9.setUserId(1);
        user9.setUserName("janedoe");

        Medication medication6 = new Medication();
        medication6.setMid(1);
        medication6.setMprescription(new ArrayList<>());
        medication6.setUser(user9);

        User user10 = new User();
        user10.setAppointment(appointment5);
        user10.setEmail("jane.doe@example.org");
        user10.setMedicalHistory("Medical History");
        user10.setMedication(medication6);
        user10.setPassword("password");
        user10.setPhone("6625550144");
        user10.setRole(Role.USER);
        user10.setUserId(1);
        user10.setUserName("janedoe");
        when(userRepository.save(Mockito.<User>any())).thenReturn(user10);
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act
        RegisterResponse actualUpdateUserResult = userCommonService.updateUser(
                new RegisterRequest("janedoe", "jane.doe@example.org", "password", "6625550144", "Medical History"), 1);

        // Assert
        verify(userRepository).findById(eq(1));
        verify(userRepository).save(isA(User.class));
        assertEquals("6625550144", actualUpdateUserResult.getPhone());
        assertEquals("Medical History", actualUpdateUserResult.getMedicalHistory());
        assertEquals("jane.doe@example.org", actualUpdateUserResult.getEmail());
        assertEquals("janedoe", actualUpdateUserResult.getUserName());
        assertEquals(1, actualUpdateUserResult.getUserId());
        assertEquals(Role.USER, actualUpdateUserResult.getRole());
    }

    /**
     * Method under test: {@link UserCommonService#updateUser(RegisterRequest, int)}
     */
    @Test
    void testUpdateUser2() {
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
        Optional<User> ofResult = Optional.of(user3);
        when(userRepository.save(Mockito.<User>any())).thenThrow(new UsernameNotFoundException("Msg"));
        when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

        // Act and Assert
        assertThrows(UsernameNotFoundException.class, () -> userCommonService.updateUser(
                new RegisterRequest("janedoe", "jane.doe@example.org", "password", "6625550144", "Medical History"), 1));
        verify(userRepository).findById(eq(1));
        verify(userRepository).save(isA(User.class));
    }
}
