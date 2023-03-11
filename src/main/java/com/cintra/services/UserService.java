package com.cintra.services;

import com.cintra.model.User;
import com.cintra.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User save(User u){
        return userRepository.save(u);
    }

    public User findById(long id){
        return userRepository.findById(id).get();
    }

    public void deleteById(long id){
        userRepository.deleteById(id);
    }

    public boolean existsById(long id){
        return userRepository.existsById(id);
    }
}
