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
            if (req == null || !req.containsKey("arquivo")) {
                return ResponseEntity.badRequest().body("O JSON deve conter o campo 'arquivo'.");
            }

            String arquivo = req.get("arquivo");

            if (arquivo == null || arquivo.trim().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body("O campo 'arquivo' está vazio. Envie o caminho completo do .bak");
            }

            System.out.println("[RESTORE] Caminho recebido: " + arquivo);

            backupService.restaurarBackup(arquivo);

            return ResponseEntity.ok("Restauração concluída com sucesso!");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Erro ao restaurar: " + e.getMessage());
        }
    }

}
