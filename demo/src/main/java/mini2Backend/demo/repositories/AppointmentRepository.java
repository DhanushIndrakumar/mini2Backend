package mini2Backend.demo.repositories;

import jakarta.transaction.Transactional;
import mini2Backend.demo.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AppointmentRepository extends JpaRepository<Appointment,Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Appointment a WHERE a.aid = :appointmentId")
    void deleteByAppointmentId(int appointmentId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Appointment a WHERE a.user.id = :userId")
    void deleteAppointmentByUserId(int userId);


    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM Appointment a WHERE a.user.id = :userId")
    boolean existsByUserId(int userId);
}
