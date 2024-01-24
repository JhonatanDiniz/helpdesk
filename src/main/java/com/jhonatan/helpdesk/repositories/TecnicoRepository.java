package com.jhonatan.helpdesk.repositories;

import com.jhonatan.helpdesk.domain.Tecnico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
}
