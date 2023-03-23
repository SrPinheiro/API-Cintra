package com.cintra.repositories;

import com.cintra.model.Doctor;
import com.cintra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    User findByToken(String token);
    User findByName(String name);
}
