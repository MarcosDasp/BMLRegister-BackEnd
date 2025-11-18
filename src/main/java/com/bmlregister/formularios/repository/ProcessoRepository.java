package com.bmlregister.formularios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmlregister.formularios.entities.Formulario;
import com.bmlregister.formularios.entities.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Integer> {
    Optional<Processo> findByFormularioId(Formulario formulario);
}
