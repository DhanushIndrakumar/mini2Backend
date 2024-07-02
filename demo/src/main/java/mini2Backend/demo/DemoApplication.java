package mini2Backend.demo;

import mini2Backend.demo.entities.Role;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private UserRepository userRepository;

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}
	public void run(String... arg){
		User adminAccount=userRepository.findByRole(Role.DOCTOR);
		if(null== adminAccount){
			User user=new User();
			user.setEmail("doctor@gmail.com");
			user.setUserName("doctor");
			user.setRole(Role.DOCTOR);
			user.setPhone("9742799303");
			user.setMedicalHistory("I am a Doctor,So not required");
			user.setPassword(new BCryptPasswordEncoder().encode("doctor"));
			userRepository.save(user);
		}
	}

}
