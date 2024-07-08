package mini2Backend.demo.DTO;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;
import mini2Backend.demo.entities.User;

import java.util.Date;

@Data
public class DoctorAppointmentResponse {
    private int aid;

    private Date adate;

    private int userId;

}
