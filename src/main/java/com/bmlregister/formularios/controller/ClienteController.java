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

import com.bmlregister.formularios.entities.Cliente;
import com.bmlregister.formularios.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class ClienteController {

    @Autowired
    private ClienteService ClienteService;

    @GetMapping
    public ResponseEntity<List<Cliente>> listarTodos() {
        List<Cliente> lista = ClienteService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable int id) {
        return ClienteService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public ResponseEntity<Cliente> incluir(@RequestBody Cliente Cliente) {
        // validacao de dados antes de inserir.
        if (!Cliente.getEmail().contains("@") || !Cliente.getEmail().contains(".")) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // pegar só o numero do telefone
        Cliente.setTelefone(Cliente.getTelefone().replaceAll("[^0-9]", ""));
        // verifica se o telefone tem 11 digitos
        if (Cliente.getTelefone().length() != 11) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        // formata o telefone para o padrão brasileiro: (xx) xxxxx-xxxx
        Cliente.setTelefone("(" + Cliente.getTelefone().substring(0, 2) + ") " + Cliente.getTelefone().substring(2, 7) + "-" + Cliente.getTelefone().substring(7));

        Cliente novo = ClienteService.incluir(Cliente);
        if (novo != null) {
            return new ResponseEntity<>(novo, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editar(@PathVariable int id, 
    @RequestBody Cliente Cliente) {
        Cliente.setIdPessoa(id);
        Cliente atualizado = ClienteService.editar(id,Cliente);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        ClienteService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
