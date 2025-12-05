package com.bmlregister.formularios.controller;

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
import com.bmlregister.formularios.entities.ProcessoFuncionario;
import com.bmlregister.formularios.entities.enums.StatusProcesso;
import com.bmlregister.formularios.repository.ClienteRepository;
import com.bmlregister.formularios.repository.FormularioRepository;
import com.bmlregister.formularios.repository.FuncionarioRepository;
import com.bmlregister.formularios.repository.ProcessoFuncionarioRepositorty;
import com.bmlregister.formularios.repository.ProcessoRepository;
import com.bmlregister.formularios.security.JwtUtil;
import com.bmlregister.formularios.service.ClienteService;
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
    private ClienteRepository clienteRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    @Autowired
    private ProcessoRepository processoRepository;

    @Autowired
    private ProcessoFuncionarioRepositorty processoFuncionarioRepositorty;


    // Obter um formulário usando o token
    @GetMapping("/{token}")
    public ResponseEntity<?> getByToken(@PathVariable String token) {
        Optional<Formulario> formOpt = formularioRepository.findByToken(token);

        if (formOpt.isEmpty()) {
            return ResponseEntity.status(404).body("Formulário não encontrado.");
        }

        return ResponseEntity.ok(formOpt.get());
    }


    // Criar um formulário vazio e gerar token
    @PostMapping("/criar")
    public ResponseEntity<?> criarFormulario(@RequestBody Map<String, Integer> body, @RequestHeader("Authorization") String authHeader) {

        String jwt = authHeader.replace("Bearer ", "");
        String loginDoFuncionario = JwtUtil.getLoginDoToken(jwt);

        // ele pega o valor do funcionario para criar um processo
        Funcionario funcionario = funcionarioRepository.findByLogin(loginDoFuncionario)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

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
        f.setCliente(cliente);
        f.setToken(token);
        formularioRepository.save(f);

        Processo p = new Processo();
        p.setCliente(f.getCliente());
        p.setStatusProcesso(StatusProcesso.ENVIADO);
        p.setFormulario(f);

        processoRepository.save(p);

        ProcessoFuncionario pf = new ProcessoFuncionario();
        pf.setFuncionario(funcionario);
        pf.setProcesso(p);
        processoFuncionarioRepositorty.save(pf);
        

        // atualiza o cliente para conter o formulario relacionado
        cliente.getFormularios().add(f);
        clienteService.editar(cliente.getIdPessoa(), cliente);
        

        String link = "https://MarcosDasp.github.io/BMLRegister-FrontEnd/formularios/index.html?token=" + token;

        return ResponseEntity.ok(Map.of(
                "token", token,
                "link", link,
                "cliente", cliente.getNome()
        ));
    }


    // Receber o submit do formulário preenchido (JS envia aqui)
    @PostMapping("/{token}/submit")
    public ResponseEntity<?> submit(@PathVariable String token, @RequestBody Formulario dados) {

        Optional<Formulario> opt = formularioRepository.findByToken(token);
        if (opt.isEmpty()) {
            return ResponseEntity.status(404).body("Formulário inválido.");
        }

        Formulario f = opt.get();

        if (f.getCliente() == null) {
            return ResponseEntity.status(500).body("Erro: formulário não possui cliente associado.");
        }

        if (f.getCnpj() != null) {
             return ResponseEntity.status(500).body("Erro: formulário já preenchido.");
        }

        // Atualiza os campos enviados
        f.setNomeEmpresa(dados.getNomeEmpresa());

        // valida dados antes de inserir

        // pega só os numeros do cnpj
        dados.setCnpj(dados.getCnpj().replaceAll("[^0-9]", ""));

        // verifica se o cnpj tem 14 digitos
        if (dados.getCnpj().length() != 14) {
            return ResponseEntity.badRequest().body("CNPJ inválido.");
        }

        // formata o cnpj para o padrão brasileiro: xx.xxx.xxx/xxxx-xx
        dados.setCnpj(dados.getCnpj().substring(0, 2) + "." + dados.getCnpj().substring(2, 5) + "." + dados.getCnpj().substring(5, 8) + "/" + dados.getCnpj().substring(8, 12) + "-" + dados.getCnpj().substring(12));

        // salva o cpnj
        f.setCnpj(dados.getCnpj());
        
        // pega só os numeros do telefone
        dados.setTelefone(dados.getTelefone().replaceAll("[^0-9]", ""));

        // verifica se o telefone tem 11 digitos
        if (dados.getTelefone().length() != 11) {
            return ResponseEntity.badRequest().body("Telefone inválido.");
        }

        // formata o telefone para o padrão brasileiro: (xx) xxxxx-xxxx
        dados.setTelefone("(" + dados.getTelefone().substring(0, 2) + ") " + dados.getTelefone().substring(2, 7) + "-" + dados.getTelefone().substring(7));

        // salva o telefone
        f.setTelefone(dados.getTelefone());
        
        f.setPrazo(dados.getPrazo());

        if (dados.getValor() <= 0) {
            return ResponseEntity.status(500).body("Erro: o valor não pode ser negativo ou zero.");
        }

        f.setValor(dados.getValor());


        formularioRepository.save(f);

        // Atualiza o processo com as informações do formulário
        Processo processo = f.getProcesso();
        if (processo == null) {
            // se o processo for null dar erro
            return ResponseEntity.badRequest().body("Erro: formulário não possui processo associado.");
        }
        processo.setPrazo(dados.getPrazo());
        processo.setStatusProcesso(StatusProcesso.PENDENTE);
        processoRepository.save(processo);

        // atualiza o cliente para conter o processo
        Cliente cliente = f.getCliente();
        cliente.getProcessos().add(processo);
        clienteService.editar(cliente.getIdPessoa(), cliente);

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
