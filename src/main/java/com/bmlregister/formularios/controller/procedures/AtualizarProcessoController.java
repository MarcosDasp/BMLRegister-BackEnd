package com.bmlregister.formularios.controller.procedures;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmlregister.formularios.service.procedures.AtualizarProcessoService;

@RestController
@RequestMapping("/api/procedures")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class AtualizarProcessoController {

    @Autowired
    private AtualizarProcessoService service;

    @PostMapping("/atualizar")
    public void atualizarProcesso(@RequestBody String nomeEmpresa, String telefoneEmpresa, String cnpj, Float valor, String observacao, Integer prazo, Integer id) {
        service.atualizarProcesso(nomeEmpresa, telefoneEmpresa, cnpj, valor, observacao, prazo, id);
    }
}

