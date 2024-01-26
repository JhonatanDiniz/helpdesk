package com.jhonatan.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jhonatan.helpdesk.domain.dtos.TecnicoDTO;
import com.jhonatan.helpdesk.domain.enums.Perfil;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Tecnico extends Pessoa{
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "tecnico")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico(Long id, String nome, String cpf, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
        this.perfis.add(Perfil.TECNICO.getCode());
    }

    public Tecnico(TecnicoDTO obj){
        this.id = obj.getId();
        this.nome = obj.getNome();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.senha = obj.getSenha();
        this.perfis.add(Perfil.TECNICO.getCode());
    }

    public Tecnico(Long id, String nome, @CPF String cpf, @Email String email, String senha, Set<Integer> perfis, LocalDate dataCadastro) {
        super(id, nome, cpf, email, senha, perfis, dataCadastro);
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico() {
        addPerfil(Perfil.TECNICO);
    }
}
