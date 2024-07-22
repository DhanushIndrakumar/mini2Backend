package mini2Backend.demo.repositories;

import mini2Backend.demo.entities.Role;
import mini2Backend.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    Optional<User> findByUserId(int userId);

    Optional<User> findByEmail(String email);

    User findByRole(Role role);



    // Query method to find a User by userName
    Optional<User> findByUserName(String userName);
}
