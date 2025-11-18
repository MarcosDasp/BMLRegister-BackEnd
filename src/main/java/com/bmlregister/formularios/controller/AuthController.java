package com.bmlregister.formularios.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bmlregister.formularios.entities.Funcionario;
import com.bmlregister.formularios.entities.dto.LoginRequest;
import com.bmlregister.formularios.entities.dto.LoginResponse;
import com.bmlregister.formularios.entities.enums.NivelAcesso;
import com.bmlregister.formularios.security.JwtUtil;
import com.bmlregister.formularios.service.AuthService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
   public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
       Optional<Funcionario> funcionarioOpt = authService.autenticar(
            loginRequest.getEmail(),
            loginRequest.getSenha()
    );

    if (funcionarioOpt.isPresent()) {
        Funcionario funcionario = funcionarioOpt.get();
        
        String token = JwtUtil.gerarToken(funcionario.getEmail(), funcionario.getNivelAcesso().name());

        LoginResponse response = new LoginResponse(
                funcionario.getIdFuncionario(),
                funcionario.getNome(),
                funcionario.getEmail(),
                funcionario.getNivelAcesso(),
                token
        );

        record LoginResponse(String token, String nome, NivelAcesso nivelAcesso, String email) {}

        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(401).body("E-mail ou senha incorretos.");
    }
    }
}
