package mini2Backend.demo.repositories;

import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MedicationRepository extends JpaRepository<Medication,Integer> {

    Medication findByUser(User user);

    @Query("SELECT m FROM Medication m WHERE m.user.userId = :userId")
    Optional<Medication> findByUserId(@Param("userId") int userId);

    //Medication findByUserId(int userId);
}
