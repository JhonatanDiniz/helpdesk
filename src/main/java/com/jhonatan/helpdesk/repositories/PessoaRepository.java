package com.jhonatan.helpdesk.repositories;

import com.jhonatan.helpdesk.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    Optional<Pessoa> findByEmail(String email);
    Optional<Pessoa> findByCpf(String cpf);

}
