package com.bmlregister.formularios.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bmlregister.formularios.entities.Cliente;
import com.bmlregister.formularios.entities.Formulario;
import com.bmlregister.formularios.entities.Funcionario;
import com.bmlregister.formularios.entities.Processo;
import com.bmlregister.formularios.entities.enums.StatusProcesso;
import com.bmlregister.formularios.repository.ClienteRepository;
import com.bmlregister.formularios.repository.FormularioRepository;
import com.bmlregister.formularios.repository.FuncionarioRepository;
import com.bmlregister.formularios.repository.ProcessoRepository;
import com.bmlregister.formularios.security.JwtUtil;
import com.bmlregister.formularios.service.FormularioService;

@RestController
@RequestMapping("/api/formularios")
@CrossOrigin(origins = "*")
public class FormularioController {

    @Autowired
    private FormularioRepository formularioRepository;

    @Autowired
    private FormularioService formularioService;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // ============================================================
    // Obter um formulário usando o token
    // ============================================================
    @GetMapping("/{token}")
    public ResponseEntity<?> getByToken(@PathVariable String token) {
        Optional<Formulario> formOpt = formularioRepository.findByToken(token);

        if (formOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Formulário não encontrado.");
        }

        return ResponseEntity.ok(formOpt.get());
    }


    // ============================================================
    // Criar um formulário vazio e gerar token
    // ============================================================
    @PostMapping("/criar")
    public ResponseEntity<?> criarFormulario(@RequestBody Map<String, Integer> body) {

        Integer clienteId = body.get("clienteId");
        if (clienteId == null) {
            return ResponseEntity.badRequest().body("Cliente não informado.");
        }

        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Cliente não encontrado.");
        }

        Cliente cliente = clienteOpt.get();

        // gera token
        String token = UUID.randomUUID().toString();

        Formulario f = new Formulario();
        f.setClienteId(cliente);
        f.setToken(token);
        formularioRepository.save(f);

        String link = "https://MarcosDasp.github.io/BMLRegister-FrontEnd/formularios/index.html?token=" + token;

        return ResponseEntity.ok(Map.of(
                "token", token,
                "link", link,
                "cliente", cliente.getNome()
        ));
    }


    // ============================================================
    // Receber o submit do formulário preenchido (JS envia aqui)
    // ============================================================
    @PostMapping("/{token}/submit")
    public ResponseEntity<?> submit(@PathVariable String token, @RequestBody Formulario dados,  @RequestHeader("Authorization") String authHeader) {

        String jwt = authHeader.replace("Bearer ", "");
        String emailDoFuncionario = JwtUtil.getEmailDoToken(jwt);

        Funcionario funcionario = funcionarioRepository.findByEmail(emailDoFuncionario)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Optional<Formulario> opt = formularioRepository.findByToken(token);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body("Formulário inválido.");
        }

        Formulario f = opt.get();

        if (f.getClienteId() == null) {
            return ResponseEntity.status(500).body("Erro: formulário não possui cliente associado.");
        }

        // Atualiza os campos enviados
        f.setNomeEmpresa(dados.getNomeEmpresa());
        f.setCnpj(dados.getCnpj());
        f.setTelefone(dados.getTelefone());
        f.setPrazo(dados.getPrazo());
        f.setValor(dados.getValor());
        formularioRepository.save(f);

        // Cria um processo automaticamente
        Processo p = new Processo();
        p.setClienteId(f.getClienteId());
        p.setFormularioId(f);
        p.setData_abertura(LocalDate.now());
        p.setFuncionarioId(funcionario);
        p.setStatusProcesso(StatusProcesso.PENDENTE);
        p.setValor(f.getValor());
        p.setPrazo(dados.getPrazo());
        processoRepository.save(p);

        return ResponseEntity.ok("Formulário enviado e processo criado!");
    }

    @GetMapping
    public ResponseEntity<List<Formulario>> listarTodos() {
        List<Formulario> formularios = formularioRepository.findAll();
        return ResponseEntity.ok(formularios);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> excluirFormulario(@PathVariable int id) {
        try {
            formularioService.excluir(id);
            return ResponseEntity.ok("Formulário excluído com sucesso.");
        } catch (Exception e) {
            return ResponseEntity.status(404).body("Formulário não encontrado.");
        }
    }
}
