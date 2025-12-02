package com.bmlregister.formularios.entities.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String login;
    private String senha;
}
