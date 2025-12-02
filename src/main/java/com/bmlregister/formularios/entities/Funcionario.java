package com.bmlregister.formularios.entities;

import java.util.List;

import com.bmlregister.formularios.entities.enums.NivelAcesso;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToMany;
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

    @Column(length = 30, nullable = false)
    private String login;
    
    @Column(length = 30, nullable = false)
    private String senha;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private NivelAcesso nivelAcesso;

    @ManyToMany(mappedBy = "funcionarios")
    private List<Processo> processos;
}
