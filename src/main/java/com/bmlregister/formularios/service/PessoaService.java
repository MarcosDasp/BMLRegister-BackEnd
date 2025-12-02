package com.bmlregister.formularios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Pessoa;
import com.bmlregister.formularios.repository.PessoaRepository;

@Service
public class PessoaService {

@Autowired
    private PessoaRepository PessoaRepository;

    public Pessoa incluir(Pessoa Pessoa) {
        return PessoaRepository.save(Pessoa);
    }
    public Pessoa editar(int id, Pessoa Pessoa) {
        // Verifique se a Pessoa existe
        Optional<Pessoa> PessoaExistente = PessoaRepository.findById(id);
        
        if (PessoaExistente.isPresent()) {
            // Atualiza a Pessoa
            Pessoa PessoaAtualizada = PessoaExistente.get();
            PessoaAtualizada.setEmail(Pessoa.getEmail()); // Atualiza os campos necessários
            PessoaAtualizada.setNome(Pessoa.getNome());
            PessoaAtualizada.setTelefone(Pessoa.getTelefone());
            return PessoaRepository.save(PessoaAtualizada);  // Salva a Pessoa atualizada
        } else {
            // Caso a Pessoa não exista, retorna null
            return null;
        }
    }
    
    public List<Pessoa> listarTodos() {
        return PessoaRepository.findAll();
    }

    public Optional<Pessoa> buscarPorId(int id) {
        return PessoaRepository.findById(id);
    }

    public void excluir(Integer id) {
        PessoaRepository.deleteById(id);
    }
}
