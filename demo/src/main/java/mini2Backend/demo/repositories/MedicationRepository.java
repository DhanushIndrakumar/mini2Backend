package mini2Backend.demo.repositories;

import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicationRepository extends JpaRepository<Medication,Integer> {

    Medication findByUser(User user);

    //Medication findByUserId(int userId);
}
