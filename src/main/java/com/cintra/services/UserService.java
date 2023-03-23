package com.cintra.services;

import com.cintra.model.User;
import com.cintra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save( User u){

        if (u.getId() != null && userRepository.existsById(u.getId()))
            u.setUpdatedAt(LocalDateTime.now());
        else{
            u.setCreatedAt(LocalDateTime.now());
            u.setUpdatedAt(LocalDateTime.now());
        }
        return userRepository.save(u);
    }

    public User findById(long id){
        return userRepository.findById(id).get();
    }

    public User findByToken(String token){
        return userRepository.findByToken(token);
    }

    public User findByName(String name){
        return userRepository.findByName(name);
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public boolean existsById(long id){
        return userRepository.existsById(id);
    }

    public long count(){
        return userRepository.count();
    }

}
