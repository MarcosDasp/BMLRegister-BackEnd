package com.bmlregister.formularios.entities.enums;

public enum NivelAcesso {
    REPRESENTANTE,   // pode enviar formulários e ver seus clientes
    ANALISTA,        // valida cadastros e aprova processos
    GERENTE          // acesso total (editar, excluir, inativar clientes)
}
