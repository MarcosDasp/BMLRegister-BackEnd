package com.bmlregister.formularios.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Builder
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "formularios")
public class Formulario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idFormulario;

    private String nomeEmpresa;
    private String cnpj;
    private String telefone;
    private int prazo;
    private float valor; 

    @Column(unique = true)
    private String token; // token único para gerar o link

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente clienteId;

    @OneToOne(mappedBy = "formularioId", cascade = CascadeType.ALL)
    private Processo processoCadastro;
}
