package com.bmlregister.formularios.controller.functions;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmlregister.formularios.service.functions.CalcularProcessoService;

@RestController
@RequestMapping("/api/functions")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class CalcularProcessoController {

    private final CalcularProcessoService calcularProcessoService;

    public CalcularProcessoController(CalcularProcessoService calcularProcessoService) {
        this.calcularProcessoService = calcularProcessoService;
    }

    @GetMapping("/total/{periodo}")
    public ResponseEntity<BigDecimal> calcularTotal(@PathVariable String periodo) {
        LocalDate data = LocalDate.parse(periodo);
        BigDecimal total = calcularProcessoService.calcularTotalProcessos(data);
        return ResponseEntity.ok(total);
    }
}
