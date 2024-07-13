package mini2Backend.demo.DTO;

import lombok.Data;

import java.util.List;

@Data
public class MedicationResponse {
    private int mId;

    private List<String> mPrescription;
}
