package com.cintra.services;

import com.cintra.model.Especialidade;
import com.cintra.model.Role;
import com.cintra.repositories.EspecialidadeRepository;
import com.cintra.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EspecialidadeService {
    @Autowired
    private EspecialidadeRepository especialidadeRepository;

    public List<Especialidade> findAll(){
        return especialidadeRepository.findAll();
    }

    public Especialidade findById(String id){
        return especialidadeRepository.findById(id).get();
    }

    public void delete(String id){
        especialidadeRepository.deleteById(id);
    }


    public boolean exists(String id){
        return especialidadeRepository.existsById(id);
    }
    public Especialidade save(Especialidade r){
        return especialidadeRepository.save(r);
    }

    public long count(){
        return especialidadeRepository.count();
    }
    
}
