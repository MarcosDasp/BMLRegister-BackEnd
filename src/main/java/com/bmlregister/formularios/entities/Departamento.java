package com.bmlregister.formularios.entities;

import java.util.List;

import com.bmlregister.formularios.entities.enums.Departamentos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Data
@Table(name = "departamentos")
public class Departamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDepartamento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Departamentos departamentos;

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios;
}
