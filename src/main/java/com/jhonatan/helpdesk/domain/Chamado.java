package com.jhonatan.helpdesk.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.jhonatan.helpdesk.domain.enums.Prioridade;
import com.jhonatan.helpdesk.domain.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Chamado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataAbertura = LocalDate.now();

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataEncerramento;

    @NotNull(message = "O campo prioridade é requerido!")
    private Prioridade prioridade;

    @NotNull(message = "O campo status é requerido!")
    private Status status;

    @Column(length = 100)
    @NotNull(message = "O campo título é requerido!")
    private String titulo;

    @NotNull(message = "O campo observações é requerido!")
    private String observacoes;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    @NotNull(message = "O técnico precisa ser informado!")
    private Tecnico tecnico;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @NotNull(message = "O cliente precisa ser informado!")
    private Cliente cliente;

    public Chamado(Long id, Prioridade prioridade, Status status, String titulo, String observacoes, Tecnico tecnico, Cliente cliente) {
        this.id = id;
        this.prioridade = prioridade;
        this.status = status;
        this.titulo = titulo;
        this.observacoes = observacoes;
        this.tecnico = tecnico;
        this.cliente = cliente;
    }
}
