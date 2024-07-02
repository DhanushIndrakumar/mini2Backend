package mini2Backend.demo.service;


import lombok.RequiredArgsConstructor;
import mini2Backend.demo.DTO.MedicationRequest;
import mini2Backend.demo.entities.Medication;
import mini2Backend.demo.entities.User;
import mini2Backend.demo.repositories.MedicationRepository;
import mini2Backend.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicationService {

    private final MedicationRepository medicationRepository;

    private final UserRepository userRepository;

    public Medication prescribeMedicine(MedicationRequest medicationRequest,int userId){
        User user=userRepository.findByUserId(userId).orElseThrow();
        Medication medication=new Medication();
        medication.setMPrescription(medicationRequest.getMPrescription());
        medication.setUser(user);
        return medication;
    }

    //remove medicine from medicine list
    public List<String> removeMedicineFromList(String medicine,int mid){
        Medication medication=medicationRepository.findById(mid).orElseThrow();
        List<String> oldMedicineList=medication.getMPrescription();
        //List<String> newMedicineList=new ArrayList<>();
        for(String m:oldMedicineList){
            if(medicine.equals(m)){
               oldMedicineList.remove(m);
            }
        }
        return oldMedicineList;
    }

    //updating medicine List
    public List<String> updateMedicineList(MedicationRequest medicationRequest,int mid){
        Medication medication=medicationRepository.findById(mid).orElseThrow();
        medication.setMPrescription(medicationRequest.getMPrescription());
        return medication.getMPrescription();
    }

    //retrieving a single medicine based on userid
    public Medication getMedicineByUserId(int userId){
        User user=userRepository.findByUserId(userId).get();
        Medication medication=medicationRepository.findByUser(user);
        return medication;
    }




}
