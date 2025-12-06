package com.bmlregister.formularios.controller;

import com.bmlregister.formularios.service.BackupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/backup")
@CrossOrigin(origins = "*")
public class BackupController {

    private final BackupService backupService;

    public BackupController(BackupService backupService) {
        this.backupService = backupService;
    }

    @GetMapping("/criar")
    public ResponseEntity<String> criarBackup() {
        try {
            String caminho = backupService.criarBackup();
            return ResponseEntity.ok("Backup criado em: " + caminho);
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao criar backup: " + e.getMessage());
        }
    }

    @PostMapping("/restaurar")
    public ResponseEntity<String> restaurarBackup(@RequestBody Map<String, String> req) {

        try {
            String arquivo = req.get("arquivo");

            if (arquivo == null || arquivo.isEmpty()) {
                return ResponseEntity.badRequest().body("Informe o arquivo .bak");
            }

            backupService.restaurarBackup(arquivo);

            return ResponseEntity.ok("Processo de restauração iniciado!");

        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao restaurar: " + e.getMessage());
        }
    }
}
