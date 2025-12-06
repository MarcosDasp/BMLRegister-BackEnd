package com.bmlregister.formularios.service.functions;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.bmlregister.formularios.repository.functions.CalcularProcessoRepository;

@Service
public class CalcularProcessoService {
   
    private final CalcularProcessoRepository calcularProcessoRepository;

    public CalcularProcessoService(CalcularProcessoRepository calcularProcessoRepository ) {
        this.calcularProcessoRepository = calcularProcessoRepository;
    }

    public BigDecimal calcularTotalProcessos(LocalDate periodo) {
        return calcularProcessoRepository.calcularTotalProcessos(periodo);
    }
}
