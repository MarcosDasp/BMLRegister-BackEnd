package com.bmlregister.formularios.service.views;

import java.text.Normalizer.Form;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Funcionario;
import com.bmlregister.formularios.entities.dto.FormularioPendenteDTO;
import com.bmlregister.formularios.repository.FuncionarioRepository;
import com.bmlregister.formularios.repository.views.FormulariosPendentesRepository;

@Service
public class FormulariosPendentesService {

    @Autowired
    private FormulariosPendentesRepository repository;

    public List<FormularioPendenteDTO> listarPendentes() { 
        List<FormularioPendenteDTO> rows = repository.consultarView();
        return rows;
    }
}
