package com.bmlregister.formularios.entities;

import java.time.LocalDateTime;

import com.bmlregister.formularios.entities.enums.Tipo;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "processo_funcionario")
public class ProcessoFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "idProcesso")
    @JsonIgnore
    private Processo processo;

    @ManyToOne
    @JoinColumn(name = "idPessoa")
    @JsonIgnore
    private Funcionario funcionario;

    @Column(nullable = false)
    private LocalDateTime dataAlteracao = LocalDateTime.now();

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Tipo tipo = Tipo.CRIADOR; // valor padrão de editor

}