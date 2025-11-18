package com.bmlregister.formularios.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bmlregister.formularios.entities.Formulario;

@Repository
public interface FormularioRepository extends JpaRepository<Formulario, Integer> {
    Optional<Formulario> findByToken(String token);
}
