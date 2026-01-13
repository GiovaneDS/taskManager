package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.controller.dto.AlterarStatusTarefaDTO;
import br.com.taskmanager.task_manager_api.domain.entity.Tarefa;
import br.com.taskmanager.task_manager_api.service.TarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    // ======================
    // CRIAR TAREFA
    // ======================
    @PostMapping
    public Tarefa criar(@RequestBody Tarefa tarefa) {
        return service.criar(tarefa);
    }

    // ======================
    // LISTAR POR PROJETO
    // ======================
    @GetMapping("/projeto/{id}")
    public List<Tarefa> listarPorProjeto(@PathVariable Long id) {
        return service.listarPorProjeto(id);
    }

    // ======================
    // MOVER STATUS
    // ======================
    @PutMapping("/{id}/status")
    public Tarefa moverStatus(
            @PathVariable Long id,
            @RequestBody AlterarStatusTarefaDTO dto) {

        return service.moverStatus(id, dto.getStatus());
    }
}
