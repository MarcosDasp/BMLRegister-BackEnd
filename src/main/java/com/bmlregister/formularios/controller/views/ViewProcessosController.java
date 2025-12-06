package com.bmlregister.formularios.controller.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmlregister.formularios.entities.dto.ProcessosDTO;
import com.bmlregister.formularios.service.views.ViewProcessosService;

@RestController
@RequestMapping("/api/views")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class ViewProcessosController {

    @Autowired
    private ViewProcessosService service;

    @GetMapping("/processos")
    public List<ProcessosDTO> listar() {
        return service.listar();
    }
}

