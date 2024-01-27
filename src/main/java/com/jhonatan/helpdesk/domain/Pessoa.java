package com.jhonatan.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jhonatan.helpdesk.domain.enums.Perfil;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public abstract class Pessoa implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @Column(length = 100)
    @NotNull(message = "O campo nome é obrigatório!")
    protected String nome;

    @Column(length = 50, unique = true)
    @NotNull(message = "O campo cpf é obrigatório!")
    protected String cpf;

    @Column(length = 100, unique = true)
    @Email
    @NotNull(message = "O campo e-mail é obrigatório!")
    protected String email;

    @Column(length = 25)
    @NotNull(message = "O campo senha é obrigatório!")
    protected String senha;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIS")
    protected Set<Integer> perfis = new HashSet<>();

    @JsonFormat(pattern = "dd/MM/yyyy")
    protected LocalDate dataCadastro = LocalDate.now();

    public Set<Perfil> getPerfis() {
        return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getCode());
    }
}
