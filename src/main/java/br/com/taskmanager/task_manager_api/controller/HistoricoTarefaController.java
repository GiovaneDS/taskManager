package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.domain.entity.HistoricoTarefa;
import br.com.taskmanager.task_manager_api.domain.repository.HistoricoTarefaRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas/{tarefaId}/historico")
public class HistoricoTarefaController {

    private final HistoricoTarefaRepository repository;

    public HistoricoTarefaController(HistoricoTarefaRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<HistoricoTarefa> listar(@PathVariable Long tarefaId) {
        return repository.findByTarefaIdOrderByDataEventoAsc(tarefaId);
    }
}
