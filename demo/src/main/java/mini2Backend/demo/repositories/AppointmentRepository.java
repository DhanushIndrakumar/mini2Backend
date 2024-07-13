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
}
