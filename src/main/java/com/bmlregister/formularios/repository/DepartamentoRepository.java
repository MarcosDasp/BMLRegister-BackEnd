package com.bmlregister.formularios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmlregister.formularios.entities.Departamento;


@Repository
public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {
        Optional<Departamento> findById(Integer departamentoId);
}
