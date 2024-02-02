package com.jhonatan.helpdesk.repositories;

import com.jhonatan.helpdesk.domain.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChamadoRepository extends JpaRepository<Chamado, Long> {
    List<Chamado> findByClienteId(Long id);
}
