package com.cintra.model;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="tb_especialidade")
public class Especialidade implements Serializable{

    @Id
    private String name;
    
    @OneToMany(mappedBy ="especialidade")
    @JsonIgnore
    private List<Doctor> doctors;

    
    public Especialidade() {
    }
    
    
    public Especialidade(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

}
