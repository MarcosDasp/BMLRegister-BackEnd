package com.bmlregister.formularios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Cliente;
import com.bmlregister.formularios.repository.ClienteRepository;

@Service
public class ClienteService {

@Autowired
    private ClienteRepository ClienteRepository;

    public Cliente incluir(Cliente Cliente) {
        return ClienteRepository.save(Cliente);
    }
    public Cliente editar(int id, Cliente Cliente) {
        // Verifique se a Cliente existe
        Optional<Cliente> ClienteExistente = ClienteRepository.findById(id);
        
        if (ClienteExistente.isPresent()) {
            // Atualiza a Cliente
            Cliente ClienteAtualizada = ClienteExistente.get();
            ClienteAtualizada.setNome(Cliente.getNome());  // Atualiza os campos necessários
            ClienteAtualizada.setEmail(Cliente.getEmail());
            ClienteAtualizada.setTelefone(Cliente.getTelefone());
            return ClienteRepository.save(ClienteAtualizada);  // Salva a Cliente atualizada
        } else {
            // Caso a Cliente não exista, retorna null
            return null;
        }
    }
    public List<Cliente> listarTodos() {
        return ClienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(int id) {
        return ClienteRepository.findById(id);
    }

    public void excluir(Integer id) {
        ClienteRepository.deleteById(id);
    }
}
