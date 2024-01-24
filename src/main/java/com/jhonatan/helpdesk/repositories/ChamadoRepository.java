package com.jhonatan.helpdesk.repositories;

import com.jhonatan.helpdesk.domain.Chamados;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChamadoRepository extends JpaRepository<Chamados, Long> {
}
