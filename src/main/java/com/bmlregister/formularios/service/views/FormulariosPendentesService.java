package com.bmlregister.formularios.service.views;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.dto.FormularioPendenteDTO;
import com.bmlregister.formularios.repository.views.FormulariosPendentesRepository;

@Service
public class FormulariosPendentesService {

    @Autowired
    private FormulariosPendentesRepository formulariosPendentesRepository;

    public List<FormularioPendenteDTO> listarPendentes() { 
        List<FormularioPendenteDTO> rows = formulariosPendentesRepository.consultarView();
        return rows;
    }
}
