package mini2Backend.demo.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="Medications")
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mId;

    private List<String> mPrescription;

    @OneToOne
    @JoinColumn(name="patient_id")
    private User user;
}
