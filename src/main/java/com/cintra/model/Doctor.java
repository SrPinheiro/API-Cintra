package com.cintra.model;

import java.io.Serializable;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tb_doctor")
public class Doctor extends User implements Serializable{
    private static final long serialVersionUID = 1l;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="especialidade_id")
    private Especialidade especialidade;

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    
    
}