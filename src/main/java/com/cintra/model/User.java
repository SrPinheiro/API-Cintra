package com.cintra.model;

import com.cintra.model.DTO.UserDTO;
import jakarta.persistence.*;

@Entity
@Table(name= "tb_user")
public class User extends UserDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    @ManyToOne
    @JoinColumn(name="role_id")
    private Roles role;

    public User() {
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
