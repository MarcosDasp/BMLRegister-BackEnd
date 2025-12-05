package com.bmlregister.formularios.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bmlregister.formularios.entities.dto.FormularioPendenteDTO;

public interface FormulariosPendentesRepository extends JpaRepository<FormularioPendenteDTO, Integer> {

    @Query(value = "SELECT * FROM vw_formularios_pendentes", nativeQuery = true)
    List<FormularioPendenteDTO> consultarView();

}

