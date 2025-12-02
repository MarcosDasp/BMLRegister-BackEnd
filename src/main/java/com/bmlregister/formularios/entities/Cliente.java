package com.bmlregister.formularios.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "idCliente")
public class Cliente extends Pessoa {

    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Processo> processos = new ArrayList<>();;

    @OneToMany(mappedBy = "clienteId", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Formulario> formularios = new ArrayList<>();
}
