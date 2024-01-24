package com.jhonatan.helpdesk.repositories;

import com.jhonatan.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
