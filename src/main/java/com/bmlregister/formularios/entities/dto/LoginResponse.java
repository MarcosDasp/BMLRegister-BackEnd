package com.bmlregister.formularios.entities.dto;

import com.bmlregister.formularios.entities.enums.NivelAcesso;
import com.bmlregister.formularios.security.JwtUtil;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private int id;
    private String nome;
    private String email;
    private NivelAcesso NivelAcesso;
    String token;
}