package com.jhonatan.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jhonatan.helpdesk.domain.dtos.ClienteDTO;
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
public class Cliente extends Pessoa{
    private static final long serialVersionUID = 1L;

    @OneToMany(mappedBy = "cliente")
    @JsonIgnore
    private List<Chamado> chamados = new ArrayList<>();

    public Cliente(Long id, String nome, String cpf, String email, String senha){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.senha = senha;
    }

    public Cliente(Long id, String nome, @CPF String cpf, @Email String email, String senha, Set<Integer> perfis, LocalDate dataCadastro) {
        super(id, nome, cpf, email, senha, perfis, dataCadastro);
        addPerfil(Perfil.CLIENTE);
    }

    public Cliente(ClienteDTO objDto) {
        this.id = objDto.getId();
        this.nome = objDto.getNome();
        this.email = objDto.getEmail();
        this.senha = objDto.getSenha();
        this.cpf = objDto.getCpf();
        this.perfis.add(Perfil.CLIENTE.getCode());
    }

    public Cliente() {
        addPerfil(Perfil.CLIENTE);
    }


}
