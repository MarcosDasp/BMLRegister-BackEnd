package com.bmlregister.formularios.entities;

import java.util.List;

import com.bmlregister.formularios.entities.enums.NivelAcesso;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@PrimaryKeyJoinColumn(name = "idFuncionario")
@Table(name = "funcionarios")
public class Funcionario extends Pessoa {

    @Column(nullable = false)
    private String login;
    
    @Column(nullable = false)
    private String senha;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAcesso nivelAcesso;

    @OneToMany(mappedBy = "funcionarioId", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Processo> processos;
}
