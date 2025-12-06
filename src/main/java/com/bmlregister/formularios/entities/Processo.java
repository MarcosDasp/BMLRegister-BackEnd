package com.bmlregister.formularios.entities;

import com.bmlregister.formularios.entities.enums.StatusProcesso;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Entity
@Setter
@Getter
@Data
@Table(name = "processos")
public class Processo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProcesso;

    private Date data_abertura;

    private Date data_validacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_processo", nullable = false)
    private StatusProcesso statusProcesso = StatusProcesso.PENDENTE; // valor padrão

    @Column(length = 150)
    private String observacoes; 

    private int prazo;
    
    @ManyToMany
    @JoinTable(
        name = "processo_funcionario",
        joinColumns = @JoinColumn(name = "idProcesso"),
        inverseJoinColumns = @JoinColumn(name = "idPessoa")
    )
    @JsonIgnore
    private List<Funcionario> historicoFuncionarios = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    @JsonIgnore
    private Cliente cliente;

    @OneToOne
    @JoinColumn(name = "formulario_id")
    @JsonIgnore
    private Formulario formulario;  
}
