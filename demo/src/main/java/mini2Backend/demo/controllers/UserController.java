package mini2Backend.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mini2Backend.demo.DTO.AppointmentResponse;
import mini2Backend.demo.DTO.RegisterRequest;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.service.AppointmentService;
import mini2Backend.demo.service.MedicationService;
import mini2Backend.demo.service.UserCommonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@CrossOrigin("*")
public class UserController {

    private final UserCommonService userCommonService;

    private final AppointmentService appointmentService;

    private final MedicationService medicationService;

    //user common operations
    @Operation(
            description="End point to get a particular user details ",
            summary = "Displays the user details through the userId given"
    )
    @GetMapping("/getUser/{userId}")
    public RegisterResponse getUser(@PathVariable int userId){
        return userCommonService.getUser(userId);
    }


    @Operation(
            description="End point to update a particular user details ",
            summary = "Displays the updated user details through the userId given"
    )
    @PutMapping("/userUpdate/{userId}")
    public RegisterResponse updateUser(@RequestBody RegisterRequest registerRequest, @PathVariable int userId){
        return userCommonService.updateUser(registerRequest,userId);
    }

    @Operation(
            description="End point to remove the user from the system ",
            summary = "Deletes a particular user details by providing the userId"
    )
    @PutMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable int userId){
         userCommonService.removeUser(userId);
         return "user successfully deleted";
    }

    //user appointment operations

    @Operation(
            description="End point to book an appointment ",
            summary = "Information needed for appointment along with userId through the path need to be given"
    )

    @PostMapping("bookAppointment/{userId}")
    public AppointmentResponse bookAppointment(@RequestBody Appointment appointment, @PathVariable int userId){
        return appointmentService.bookAppointment(appointment,userId);
    }

    @DeleteMapping("cancelAppointment/{userId}")
    public String cancelAppointmentByUser(@PathVariable int userId){
        return appointmentService.cancelAppointmentByUser(userId);
    }


    @Operation(
            description="End point to get the details of appointment made by particular user ",
            summary = "Displays the details of a single appointment made by the user"
    )
    @GetMapping("getAppointment/{appointmentId}")
    public AppointmentResponse getAppointmentById(@PathVariable int appointmentId){
        return appointmentService.getAppointmentById(appointmentId);
    }

    //getMedication based on userId
    @Operation(
            description="End point to get the medication prescribed by the doctor",
            summary = "Displays the details of the medication"
    )
    @GetMapping("getMedication/{userId}")
    public ResponseEntity<?> getMedicineByUserId(@PathVariable int userId) {
        return medicationService.getMedicineByUserId(userId);
    }


}
