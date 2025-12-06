package com.bmlregister.formularios.entities.dto;

import java.sql.Date;

public record ProcessosDTO(
    String nome,
    String email,
    String telefone_cliente,
    String cnpj,
    String nome_empresa,
    String telefone_empresa,
    float valor,
    String statusProcesso,
    Date data_abertura,
    Date data_validacao,
    String observacao,
    int prazo,
    int id_form
) {}
