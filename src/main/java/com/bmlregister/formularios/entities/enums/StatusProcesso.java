package com.bmlregister.formularios.entities.enums;

public enum StatusProcesso {
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
