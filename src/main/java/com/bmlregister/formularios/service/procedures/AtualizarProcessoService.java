package com.bmlregister.formularios.service.procedures;

import org.springframework.stereotype.Service;

import com.bmlregister.formularios.repository.procedures.AtualizarProcessoRepository;

@Service
public class AtualizarProcessoService {
    
    private final AtualizarProcessoRepository atualizarProcessoRepository;

    public AtualizarProcessoService(AtualizarProcessoRepository atualizarProcessoRepository) {
        this.atualizarProcessoRepository = atualizarProcessoRepository;
    }

    public void atualizarProcesso(String nomeEmpresa, String telefoneEmpresa, String cnpj, Float valor, String observacao, Integer prazo, Integer id) {
        atualizarProcessoRepository.atualizarProcesso(nomeEmpresa, telefoneEmpresa, cnpj, valor, observacao, prazo, id);
    }
}
