package mini2Backend.demo.service;

import mini2Backend.demo.entities.Appointment;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.AppointmentRepository;
import mini2Backend.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;


    public Appointment bookAppointment(Appointment appointment,int userId) {
        Appointment appointment1=new Appointment();
        appointment1.setAdate(appointment.getAdate());
        User user= userRepository.findByUserId(userId).orElseThrow();
        appointment1.setUser(user);
        //the appointmentId should get stored in  user repository without affecting the functionality
        return  appointmentRepository.save(appointment1);
    }

    public void cancelAppointment(int aid){
        appointmentRepository.deleteById(aid);
    }

    public List<Appointment> getAllAppointments(){
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(int aid){
        return appointmentRepository.findById(aid).orElseThrow();
    }

}
