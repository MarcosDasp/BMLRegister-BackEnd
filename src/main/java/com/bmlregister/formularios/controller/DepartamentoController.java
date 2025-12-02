package com.bmlregister.formularios.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bmlregister.formularios.entities.Pessoa;
import com.bmlregister.formularios.service.PessoaService;

@RestController
@RequestMapping("/api/departamentos")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class DepartamentoController {

    @Autowired
    private PessoaService DepartamentoService;

    @GetMapping
    public ResponseEntity<List<Pessoa>> listarTodos() {
        List<Pessoa> lista = DepartamentoService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> buscarPorId(@PathVariable int id) {
        return DepartamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Pessoa> incluir(@RequestBody 
    Pessoa Departamento) {
        Pessoa novo = DepartamentoService.incluir(Departamento);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pessoa> editar(@PathVariable int id, 
    @RequestBody Pessoa Departamento) {
        Pessoa atualizado = DepartamentoService.editar(id,Departamento);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        DepartamentoService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
