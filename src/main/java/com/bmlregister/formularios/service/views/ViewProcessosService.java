package com.bmlregister.formularios.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.dto.ProcessosDTO;
import com.bmlregister.formularios.repository.views.ViewProcessosRepository;

@Service
public class ViewProcessosService {

    @Autowired
    private ViewProcessosRepository viewProcessosRepository;

    public List<ProcessosDTO> listar() { 
        List<ProcessosDTO> rows = viewProcessosRepository.consultarView();
        return rows;
    }
}
