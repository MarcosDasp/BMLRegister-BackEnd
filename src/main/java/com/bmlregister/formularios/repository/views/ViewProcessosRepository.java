package com.bmlregister.formularios.repository.views;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bmlregister.formularios.entities.Processo;
import com.bmlregister.formularios.entities.dto.ProcessosDTO;

public interface ViewProcessosRepository extends JpaRepository<Processo, Long> {

    @Query(value = "SELECT * FROM vw_processos", nativeQuery = true)
    List<ProcessosDTO> consultarView();

}

