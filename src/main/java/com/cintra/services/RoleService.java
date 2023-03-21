package com.cintra.services;

import com.cintra.model.Role;
import com.cintra.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findAll(){
        return roleRepository.findAll();
    }

    public Role findById(long id){
        return roleRepository.findById(id).get();
    }

    public void delete(long id){
        roleRepository.deleteById(id);
    }

    public Role findByName(String name){
        return roleRepository.findByName(name);
    }

    public boolean exists(long id){
        return roleRepository.existsById(id);
    }
    public Role save(Role r){
        return roleRepository.save(r);
    }
    


}
