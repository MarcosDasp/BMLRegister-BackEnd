package com.bmlregister.formularios.entities.enums;

public enum StatusProcesso {
    ENVIADO("Enviado para o cliente"),
    PENDENTE("Aguardando análise"),
    APROVADO("Aprovado"),
    REPROVADO("Reprovado");

    private final String descricao;

    StatusProcesso(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
