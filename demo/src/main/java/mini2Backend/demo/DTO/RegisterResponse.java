package mini2Backend.demo.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mini2Backend.demo.entities.Role;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {

    private int userId;
    private String userName;
    private String email;
    private String phone;
    private String medicalHistory;
    private Role role;
}
