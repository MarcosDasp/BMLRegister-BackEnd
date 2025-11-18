package com.bmlregister.formularios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Formulario;
import com.bmlregister.formularios.repository.FormularioRepository;

@Service
public class FormularioService {

@Autowired
    private FormularioRepository FormularioRepository;

    public Formulario incluir(Formulario Formulario) {
        return FormularioRepository.save(Formulario);
    }
    public Formulario editar(int id, Formulario Formulario) {
        // Verifique se a Formulario existe
        Optional<Formulario> FormularioExistente = FormularioRepository.findById(id);
        
        if (FormularioExistente.isPresent()) {
            // Atualiza a Formulario
            Formulario FormularioAtualizada = FormularioExistente.get();
            FormularioAtualizada.setCnpj(Formulario.getCnpj());  // Atualiza os campos necessários
            FormularioAtualizada.setNomeEmpresa(Formulario.getNomeEmpresa());
            FormularioAtualizada.setPrazo(Formulario.getPrazo());
            FormularioAtualizada.setTelefone(Formulario.getTelefone());
            return FormularioRepository.save(FormularioAtualizada);  // Salva a Formulario atualizada
        } else {
            // Caso a Formulario não exista, retorna null
            return null;
        }
    }
    public List<Formulario> listarTodos() {
        return FormularioRepository.findAll();
    }

    public Optional<Formulario> buscarPorId(int id) {
        return FormularioRepository.findById(id);
    }

    public void excluir(Integer id) {
        FormularioRepository.deleteById(id);
    }
}
