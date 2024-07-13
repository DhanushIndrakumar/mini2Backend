package mini2Backend.demo.service;

import mini2Backend.demo.DTO.AppointmentResponse;
import mini2Backend.demo.DTO.DoctorAppointmentResponse;
import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.AppointmentRepository;
import mini2Backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;


    public AppointmentResponse bookAppointment(Appointment appointment,int userId) {
        Appointment appointment1=new Appointment();
        appointment1.setAdate(appointment.getAdate());
        User user= userRepository.findByUserId(userId).orElseThrow();
        appointment1.setUser(user);
        //the appointmentId should get stored in  user repository without affecting the functionality
        Appointment appointment2= appointmentRepository.save(appointment1);
        AppointmentResponse appointmentResponse=new AppointmentResponse();
        appointmentResponse.setAid(appointment2.getAid());
        appointmentResponse.setAdate(appointment2.getAdate());
        return appointmentResponse;
    }

    public void cancelAppointment(int appointmentId){
        appointmentRepository.deleteByAppointmentId(appointmentId);
    }

    public List<DoctorAppointmentResponse> getAllAppointments(){
        List<Appointment> appointmentList=appointmentRepository.findAll();
        List<DoctorAppointmentResponse> responseList=new ArrayList<>();
        for(Appointment appointment:appointmentList){
            DoctorAppointmentResponse doctorAppointmentResponse=new DoctorAppointmentResponse();
            doctorAppointmentResponse.setAid(appointment.getAid());
            doctorAppointmentResponse.setAdate(appointment.getAdate());
            doctorAppointmentResponse.setUserId(appointment.getUser().getUserId());
            responseList.add(doctorAppointmentResponse);
        }
        return responseList;

    }

    public AppointmentResponse getAppointmentById(int aid){
        AppointmentResponse appointmentResponse=new AppointmentResponse();
        Appointment appointment=appointmentRepository.findById(aid).orElseThrow();
        appointmentResponse.setAid(appointment.getAid());
        appointmentResponse.setAdate(appointment.getAdate());
        return appointmentResponse;
    }

}
