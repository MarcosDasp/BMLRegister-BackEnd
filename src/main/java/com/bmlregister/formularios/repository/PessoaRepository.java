package com.bmlregister.formularios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmlregister.formularios.entities.Pessoa;


@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer> {
        Optional<Pessoa> findById(Integer pessoaId);
}
