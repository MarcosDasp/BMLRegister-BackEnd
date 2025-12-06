package com.bmlregister.formularios.repository.functions;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bmlregister.formularios.entities.Formulario;

@Repository
public interface CalcularProcessoRepository extends JpaRepository<Formulario, Integer> {

    @Query(
    value = "SELECT * FROM dbo.fc_calcTotalProcessos(:data)", 
    nativeQuery = true
    )
    BigDecimal calcularTotalProcessos(@Param("data") LocalDate periodo);

}
