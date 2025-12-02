package com.bmlregister.formularios.controller;

import java.util.List;
import java.util.Map;

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

import com.bmlregister.formularios.entities.Funcionario;
import com.bmlregister.formularios.entities.enums.NivelAcesso;
import com.bmlregister.formularios.service.FuncionarioService;

@RestController
@RequestMapping("/api/funcionarios")
@CrossOrigin(origins = "*") // Permite que o frontend acesse a API
public class FuncionarioController {

    @Autowired
    private FuncionarioService FuncionarioService;

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        List<Funcionario> lista = FuncionarioService.listarTodos();
        return ResponseEntity.ok().body(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable int id) {
        return FuncionarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

        @PostMapping
        public ResponseEntity<Funcionario> incluir(@RequestBody Map<String, Object> body) {
            try {
                String nome = (String) body.get("nome");
                String email = (String) body.get("email");
                String login = (String) body.get("login");
                String senha = (String) body.get("senha");
                String telefone = (String) body.get("telefone");
                String nivelAcesso = (String) body.get("nivelAcesso");

                Funcionario f = new Funcionario();
                f.setNome(nome);
                f.setEmail(email);
                f.setLogin(login);
                f.setSenha(senha);
                f.setTelefone(telefone);
                f.setNivelAcesso(NivelAcesso.valueOf(nivelAcesso));

                Funcionario novo = FuncionarioService.incluir(f);
                return new ResponseEntity<>(novo, HttpStatus.CREATED);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }


     @PutMapping("/{id}")
    public ResponseEntity<Funcionario> editar(@PathVariable int id, 
    @RequestBody Funcionario Funcionario) {
        Funcionario atualizado = FuncionarioService.editar(id,Funcionario);
        if (atualizado != null) {
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable int id) {
        FuncionarioService.excluir(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
}
