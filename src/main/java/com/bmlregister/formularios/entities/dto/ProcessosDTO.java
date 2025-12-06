package com.bmlregister.formularios.entities.dto;

import java.time.LocalDate;

public record ProcessosDTO(
    String nome,
    String email,
    String telefone_cliente,
    String cnpj,
    String nome_empresa,
    String telefone_empresa,
    float valor,
    String statusProcesso,
    LocalDate data_abertura,
    LocalDate data_validacao,
    String observacao,
    int prazo,
    int id_form
) {}
