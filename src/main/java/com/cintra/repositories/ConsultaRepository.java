package com.cintra.repositories;

import com.cintra.model.Consulta;
import com.cintra.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

}
