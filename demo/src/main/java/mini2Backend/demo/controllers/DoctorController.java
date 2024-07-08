package mini2Backend.demo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import mini2Backend.demo.DTO.AppointmentResponse;
import mini2Backend.demo.DTO.DoctorAppointmentResponse;
import mini2Backend.demo.DTO.MedicationRequest;
import mini2Backend.demo.DTO.RegisterResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.service.AppointmentService;
import mini2Backend.demo.service.MedicationService;
import mini2Backend.demo.service.UserCommonService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/doctor")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DoctorController {

    private final UserCommonService userCommonService;

    private final AppointmentService appointmentService;

    private final MedicationService medicationService;

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
    public List<DoctorAppointmentResponse> getAppointments(){

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
            summary = "Allows the doctor to prescribe medicine to the patient"
    )
    @PostMapping("/prescribeMedicine/{userId}")
    public Medication prescribeMedicine(@RequestBody MedicationRequest medicationRequest,@PathVariable int userId){
        return medicationService.prescribeMedicine(medicationRequest,userId);
    }

    @Operation(
            description="End point to remove a particular medicine from medicine list ",
            summary = "Allows the doctor to remove the unwanted medicine from  the medicine list"
    )
    @GetMapping("/removeMedicineFromList/{mId}")
    public List<String> removeMedicineFromList(@RequestBody String med,@PathVariable int mId){
        return medicationService.removeMedicineFromList(med,mId);
    }

    @Operation(
            description="End point to edit the medicine list ",
            summary = "Allows the doctor to modify the medicine list based on patient condition"
    )
    @GetMapping("/editMedicineList/{mId}")
    public List<String>editMedicineList(@RequestBody MedicationRequest medicationRequest,@PathVariable int mId){
        return medicationService.updateMedicineList(medicationRequest,mId);
    }


}

