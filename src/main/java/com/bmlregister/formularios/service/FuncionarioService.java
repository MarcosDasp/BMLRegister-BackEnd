package com.bmlregister.formularios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Departamento;
import com.bmlregister.formularios.entities.Funcionario;
import com.bmlregister.formularios.repository.DepartamentoRepository;
import com.bmlregister.formularios.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository FuncionarioRepository;

    @Autowired
    private DepartamentoRepository departamentoRepository; 


    public Funcionario incluir(Funcionario Funcionario, Integer departamentoId) {
        Departamento departamento = departamentoRepository.findById(departamentoId)
        .orElseThrow(() -> new RuntimeException("Departamento não encontrado"));

        Funcionario.setDepartamento(departamento);

        return FuncionarioRepository.save(Funcionario);
    }

    public Funcionario editar(int id, Funcionario Funcionario) {
        // Verifique se a Funcionario existe
        Optional<Funcionario> FuncionarioExistente = FuncionarioRepository.findById(id);
        
        if (FuncionarioExistente.isPresent()) {
            // Atualiza a Funcionario
            Funcionario FuncionarioAtualizada = FuncionarioExistente.get();
            FuncionarioAtualizada.setEmail(Funcionario.getEmail());  // Atualiza os campos necessários
            FuncionarioAtualizada.setNome(Funcionario.getNome());
            FuncionarioAtualizada.setTelefone(Funcionario.getTelefone());
            FuncionarioAtualizada.setNivelAcesso(Funcionario.getNivelAcesso());
            FuncionarioAtualizada.setDepartamento(Funcionario.getDepartamento());
            return FuncionarioRepository.save(FuncionarioAtualizada);  // Salva a Funcionario atualizada
        } else {
            // Caso a Funcionario não exista, retorna null
            return null;
        }
    }
    public List<Funcionario> listarTodos() {
        return FuncionarioRepository.findAll();
    }

    public Optional<Funcionario> buscarPorId(int id) {
        return FuncionarioRepository.findById(id);
    }

    public void excluir(Integer id) {
        FuncionarioRepository.deleteById(id);
    }
}
