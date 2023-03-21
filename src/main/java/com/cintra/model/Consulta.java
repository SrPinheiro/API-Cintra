package com.cintra.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "tb_consultas")
public class Consulta implements Serializable{
    private static final long serialVersionUID = 1l;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String doctor;
    @ManyToOne
    private User client;
    private String data_de_marcacao;
    private String data_da_consulta;
    private Double preco;
    @Lob
    private String comentario;

    

    public Consulta() {
    }

    
    public Consulta(long id, String doctor, User client, String data_de_marcacao, String data_da_consulta,
            Double preco, String comentario) {
        this.id = id;
        this.doctor = doctor;
        this.client = client;
        this.data_de_marcacao = data_de_marcacao;
        this.data_da_consulta = data_da_consulta;
        this.preco = preco;
        this.comentario = comentario;
    }


    public String getDoctor() {
        return doctor;
    }
    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }
    public User getClient() {
        return client;
    }
    public void setClient(User client) {
        this.client = client;
    }
    public String getData_de_marcacao() {
        return data_de_marcacao;
    }
    public void setData_de_marcacao(String data_de_marcacao) {
        this.data_de_marcacao = data_de_marcacao;
    }
    public String getData_da_consulta() {
        return data_da_consulta;
    }
    public void setData_da_consulta(String data_da_consulta) {
        this.data_da_consulta = data_da_consulta;
    }
    public Double getPreco() {
        return preco;
    }
    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public boolean participa(User user){
        return user.equals(doctor) || user.equals(client);
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    
}
