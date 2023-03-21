package com.cintra.services;

import com.cintra.model.Consulta;
import com.cintra.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ConsultaService {
    @Autowired
    private ConsultaRepository consultaRepository;

    public List<Consulta> findAll(){
        return consultaRepository.findAll();
    }

    public Consulta findById(long id){
        return consultaRepository.findById(id).get();
    }

    public void delete(long id){
        consultaRepository.deleteById(id);
    }

    public boolean exists(long id){
        return consultaRepository.existsById(id);
    }
    public Consulta save(Consulta r){
        return consultaRepository.save(r);
    }
}
