package com.cintra.repositories;

import com.cintra.model.Especialidade;
import com.cintra.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EspecialidadeRepository extends JpaRepository<Especialidade, String> {

}
