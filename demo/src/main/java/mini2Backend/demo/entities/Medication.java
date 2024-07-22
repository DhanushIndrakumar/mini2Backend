package mini2Backend.demo.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import mini2Backend.demo.DTO.StringListConverter;

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
    private int mid;

    @Convert(converter = StringListConverter.class)
    private List<String> mprescription;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name="patient_id")
    private User user;
}
