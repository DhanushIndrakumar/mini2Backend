package mini2Backend.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.service.AppointmentService;
import mini2Backend.demo.service.UserCommonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorController {

    private final UserCommonService userCommonService;

    private final AppointmentService appointmentService;

    //doctor operations related to user
    @Operation(
            description="End point which is  by admin to get all users",
            summary="List of users is being displayed"
    )
    @GetMapping("/getAllUsers")
    public List<RegisterResponse> getAllUsers(){
        return userCommonService.getAllUsers();
    }

    @Operation(
            description="End point to get all appointments ",
            summary = "Displays the appointments booked by all users"
    )
    @GetMapping("/getAppointments")
    public List<Appointment> getAppointments(){
        return appointmentService.getAllAppointments();
    }

    @Operation(
            description="End point to cancel the appointment ",
            summary = "Deletes the appointment made by the user"
    )
    @DeleteMapping("/deleteAppointment/{appointmentId}")
    public String deleteAppointmentById(@PathVariable int appointmentId){
        appointmentService.cancelAppointment(appointmentId);
        return "appointment successfully deleted";
    }

    //DoctorOperations with regard to medicine
    @Operation(
            description="End point to prescribe the medicine ",
            summary = "Deletes the appointment made by the user"
    )
    @DeleteMapping("/deleteAppointment/{appointmentId}")
    public String deleteAppointmentById(@PathVariable int appointmentId){
        appointmentService.cancelAppointment(appointmentId);
        return "appointment successfully deleted";
    }


}

