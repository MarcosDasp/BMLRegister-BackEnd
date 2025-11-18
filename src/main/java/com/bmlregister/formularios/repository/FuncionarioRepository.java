package com.bmlregister.formularios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmlregister.formularios.entities.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    Optional<Funcionario> findByEmailAndSenha(String email, String senha);
    Optional<Funcionario> findByEmail(String email);

}
