package com.bmlregister.formularios.controller.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmlregister.formularios.entities.dto.AtualizarProcessoDTO;
import com.bmlregister.formularios.service.procedures.AtualizarProcessoService;

@RestController
@RequestMapping("/api/procedures")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class AtualizarProcessoController {

    @Autowired
    private AtualizarProcessoService service;

    @PostMapping("/atualizar")
    public void atualizarProcesso(@RequestBody AtualizarProcessoDTO dto) {
            service.atualizarProcesso(
        dto.nomeEmpresa,
        dto.telefoneEmpresa,
        dto.cnpj,
        dto.valor,
        dto.observacao,
        dto.prazo,
        dto.id
        );
    }
}

