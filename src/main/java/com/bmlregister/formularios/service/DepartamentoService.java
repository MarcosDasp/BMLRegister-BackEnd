package com.bmlregister.formularios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Departamento;
import com.bmlregister.formularios.repository.DepartamentoRepository;

@Service
public class DepartamentoService {

@Autowired
    private DepartamentoRepository DepartamentoRepository;

    public Departamento incluir(Departamento Departamento) {
        return DepartamentoRepository.save(Departamento);
    }
    public Departamento editar(int id, Departamento Departamento) {
        // Verifique se a Departamento existe
        Optional<Departamento> DepartamentoExistente = DepartamentoRepository.findById(id);
        
        if (DepartamentoExistente.isPresent()) {
            // Atualiza a Departamento
            Departamento DepartamentoAtualizada = DepartamentoExistente.get();
            DepartamentoAtualizada.setDepartamento(Departamento.getDepartamento()); // Atualiza os campos necessários
            return DepartamentoRepository.save(DepartamentoAtualizada);  // Salva a Departamento atualizada
        } else {
            // Caso a Departamento não exista, retorna null
            return null;
        }
    }
    public List<Departamento> listarTodos() {
        return DepartamentoRepository.findAll();
    }

    public Optional<Departamento> buscarPorId(int id) {
        return DepartamentoRepository.findById(id);
    }

    public void excluir(Integer id) {
        DepartamentoRepository.deleteById(id);
    }
}
