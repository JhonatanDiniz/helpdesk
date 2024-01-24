package com.jhonatan.helpdesk.repositories;

import com.jhonatan.helpdesk.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}
