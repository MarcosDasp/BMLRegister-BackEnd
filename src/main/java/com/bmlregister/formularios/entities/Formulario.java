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

    @Column(length = 50, nullable = false)
    private String nomeEmpresa;

    @Column(length = 18, nullable = false)
    private String cnpj;

    @Column(length = 15, nullable = false)
    private String telefone;

    @Column(nullable = false)
    private int prazo;

    @Column(nullable = false)
    private float valor; 

    @Column(unique = true, nullable = false)
    private String token; // token único para gerar o link

    @ManyToOne
    @JoinColumn(name = "clienteId", nullable = false)
    private Cliente clienteId;

    @OneToOne(mappedBy = "formularioId", cascade = CascadeType.ALL)
    private Processo processo;
}
