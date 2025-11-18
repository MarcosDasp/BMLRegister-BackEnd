package com.bmlregister.formularios.entities;

import com.bmlregister.formularios.entities.enums.StatusProcesso;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Data
@Table(name = "processos")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProcesso;

    @ManyToOne
    @JoinColumn(name = "funcionarioId", nullable = false)
    private Funcionario funcionarioId;

    @ManyToOne
    @JoinColumn(name = "clienteId")
    private Cliente clienteId;

    @OneToOne
    @JoinColumn(name = "formularioId")
    @JsonIgnore
    private Formulario formularioId;  

    private LocalDate data_abertura;
    private LocalDate data_validacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_processo", nullable = false)
    private StatusProcesso statusProcesso = StatusProcesso.PENDENTE; // valor padrão

    private String observacoes; 

    private float valor;

    private int prazo;
}
