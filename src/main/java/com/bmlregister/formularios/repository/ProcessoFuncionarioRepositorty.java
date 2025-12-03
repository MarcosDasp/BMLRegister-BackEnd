package com.bmlregister.formularios.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmlregister.formularios.entities.ProcessoFuncionario;

@Repository
public interface ProcessoFuncionarioRepositorty extends JpaRepository<ProcessoFuncionario, Integer> {
}
