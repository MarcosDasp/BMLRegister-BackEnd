package com.bmlregister.formularios.entities.dto;

public record FormularioPendenteDTO(
    String nome,
    String email,
    String telefone,
    String statusProcesso,
    String token,
    Integer id
) {}
