package com.bmlregister.formularios.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Funcionario;
import com.bmlregister.formularios.repository.FuncionarioRepository;

@Service
public class AuthService {
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Optional<Funcionario> autenticar(String login, String senha) {
        return funcionarioRepository.findByLoginAndSenha(login, senha);
    }
}
