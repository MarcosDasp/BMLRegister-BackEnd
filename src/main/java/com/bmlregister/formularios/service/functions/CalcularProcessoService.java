package com.bmlregister.formularios.service.functions;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.bmlregister.formularios.repository.ProcessoRepository;

public class CalcularProcessoService {
   private final CalcularProcessoRespository processoRepository;

    public ProcessoService(ProcessoRepository processoRepository) {
        this.processoRepository = processoRepository;
    }

    public BigDecimal calcularTotalProcessos(LocalDate periodo) {
        return processoRepository.calcularTotalProcessos(periodo);
    }
}
