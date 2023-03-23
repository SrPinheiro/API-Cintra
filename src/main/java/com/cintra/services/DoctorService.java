package com.cintra.services;

import com.cintra.model.Doctor;
import com.cintra.model.User;
import com.cintra.repositories.DoctorRepository;
import com.cintra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public List<Doctor> findAll(){
        return doctorRepository.findAll();
    }

    public Doctor save( Doctor u){

        if (u.getId() != null && doctorRepository.existsById(u.getId()))
            u.setUpdatedAt(LocalDateTime.now());
        else{
            u.setCreatedAt(LocalDateTime.now());
            u.setUpdatedAt(LocalDateTime.now());
        }
        return doctorRepository.save(u);
    }

    public Doctor findById(long id){
        return doctorRepository.findById(id).get();
    }

    public Doctor findByToken(String token){
        return (Doctor) doctorRepository.findByToken(token);
    }

    public Doctor findByName(String name){
        return (Doctor) doctorRepository.findByName(name);
    }

    public void deleteById(long id){
        doctorRepository.deleteById(id);
    }

    public boolean existsById(long id){
        return doctorRepository.existsById(id);
    }

    public long count(){
        return doctorRepository.count();
    }

}
