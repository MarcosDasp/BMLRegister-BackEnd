package com.bmlregister.formularios.controller.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.bmlregister.formularios.entities.dto.FormularioPendenteDTO;
import com.bmlregister.formularios.service.views.FormulariosPendentesService;

public class FormulariosPendentesController {

    @Autowired
    private FormulariosPendentesService service;

    @GetMapping("/pendentes")
    public List<FormularioPendenteDTO> listar() {
        return service.listarPendentes();
    }
}

