package com.cintra.repositories;

import com.cintra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByToken(String token);
}
