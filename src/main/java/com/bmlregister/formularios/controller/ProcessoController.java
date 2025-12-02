package com.bmlregister.formularios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bmlregister.formularios.entities.Processo;
import com.bmlregister.formularios.entities.enums.StatusProcesso;
import com.bmlregister.formularios.service.ProcessoService;

@RestController
@RequestMapping("/api/processos")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class ProcessoController {

    @Autowired
    private ProcessoService ProcessoService;

    @GetMapping
    public ResponseEntity<List<Processo>> listarTodos() {
        List<Processo> lista = ProcessoService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Processo> buscarPorId(@PathVariable int id) {
        return ProcessoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Processo> incluir(@RequestBody 
    Processo Processo) {        
        Processo novo = ProcessoService.incluir(Processo);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Processo> editar(@PathVariable int id, 
    @RequestBody Processo Processo) {
        Processo atualizado = ProcessoService.editar(id,Processo);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        ProcessoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Processo> atualizarStatus(
            @PathVariable int id,
            @RequestParam StatusProcesso status) {
        try {
            Processo atualizado = ProcessoService.atualizarStatus(id, status);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
