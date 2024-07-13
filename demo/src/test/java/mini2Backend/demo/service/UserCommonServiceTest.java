package mini2Backend.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import mini2Backend.demo.AuthImplementation.JWTServiceImpl;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.entities.Role;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserCommonService.class, PasswordEncoder.class, AuthenticationManager.class})
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

  @Test
  void testGetAllUsers() {
    // Arrange
    when(userRepository.findAll()).thenReturn(new ArrayList<>());

    // Act
    List<RegisterResponse> actualAllUsers = userCommonService.getAllUsers();

    // Assert
    verify(userRepository).findAll();
    assertTrue(actualAllUsers.isEmpty());
  }

  @Test
  void testGetAllUsers2() {
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

    ArrayList<User> userList = new ArrayList<>();
    userList.add(user3);
    when(userRepository.findAll()).thenReturn(userList);

    // Act
    List<RegisterResponse> actualAllUsers = userCommonService.getAllUsers();

    // Assert
    verify(userRepository).findAll();
    assertEquals(1, actualAllUsers.size());
    RegisterResponse getResult = actualAllUsers.get(0);
    assertEquals("6625550144", getResult.getPhone());
    assertEquals("Medical History", getResult.getMedicalHistory());
    assertEquals("jane.doe@example.org", getResult.getEmail());
    assertEquals("janedoe", getResult.getUserName());
    assertEquals(1, getResult.getUserId());
    assertEquals(Role.USER, getResult.getRole());
  }


  @Test
  void testGetUser() {


    // Arrange
    User user = mock(User.class);
    when(user.getUserId()).thenReturn(1);
    when(user.getEmail()).thenReturn("jane.doe@example.org");
    when(user.getMedicalHistory()).thenReturn("Medical History");
    when(user.getPhone()).thenReturn("6625550144");
    when(user.getUserName()).thenReturn("janedoe");
    when(user.getRole()).thenReturn(Role.USER);
    Optional<User> ofResult = Optional.of(user);
    UserRepository userRepository = mock(UserRepository.class);
    when(userRepository.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    ArrayList<AuthenticationProvider> providers = new ArrayList<>();
    providers.add(new RunAsImplAuthenticationProvider());
    ProviderManager authenticationManager = new ProviderManager(providers);
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // Act
    RegisterResponse actualUser = (new UserCommonService(userRepository, passwordEncoder, authenticationManager,
        new JWTServiceImpl())).getUser(1);

    // Assert
    verify(user).getEmail();
    verify(user).getMedicalHistory();
    verify(user).getPhone();
    verify(user).getRole();
    verify(user).getUserId();
    verify(user).getUserName();
    verify(userRepository).findById(eq(1));
    assertEquals("6625550144", actualUser.getPhone());
    assertEquals("Medical History", actualUser.getMedicalHistory());
    assertEquals("jane.doe@example.org", actualUser.getEmail());
    assertEquals("janedoe", actualUser.getUserName());
    assertEquals(1, actualUser.getUserId());
    assertEquals(Role.USER, actualUser.getRole());
  }






}
