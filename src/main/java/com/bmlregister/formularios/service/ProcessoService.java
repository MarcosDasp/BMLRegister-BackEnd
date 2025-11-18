package com.bmlregister.formularios.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bmlregister.formularios.entities.Processo;
import com.bmlregister.formularios.entities.enums.StatusProcesso;
import com.bmlregister.formularios.repository.ProcessoRepository;

@Service
public class ProcessoService {

@Autowired
    private ProcessoRepository ProcessoRepository;

    public Processo incluir(Processo Processo) {
        Processo.setStatusProcesso(StatusProcesso.PENDENTE);
        Processo.setData_abertura(LocalDate.now());
        return ProcessoRepository.save(Processo);
    }
    public Processo editar(int id, Processo Processo) {
        // Verifique se a Processo existe
        Optional<Processo> ProcessoExistente = ProcessoRepository.findById(id);
        
        if (ProcessoExistente.isPresent()) {
            // Atualiza a Processo
            Processo ProcessoAtualizada = ProcessoExistente.get();
            ProcessoAtualizada.setStatusProcesso(Processo.getStatusProcesso());  // Atualiza os campos necessários
            ProcessoAtualizada.setFormularioId(Processo.getFormularioId());
            ProcessoAtualizada.setFuncionarioId(Processo.getFuncionarioId());
            return ProcessoRepository.save(ProcessoAtualizada);  // Salva a Processo atualizada
        } else {
            // Caso a Processo não exista, retorna null
            return null;
        }
    }

    public Processo atualizarStatus(int id, StatusProcesso novoStatus) {
        Processo processo = ProcessoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Processo não encontrado com ID: " + id));
        processo.setStatusProcesso(novoStatus);
        return ProcessoRepository.save(processo);
    }

    public List<Processo> listarTodos() {
        return ProcessoRepository.findAll();
    }

    public Optional<Processo> buscarPorId(int id) {
        return ProcessoRepository.findById(id);
    }

    public void excluir(Integer id) {
        ProcessoRepository.deleteById(id);
    }
}
