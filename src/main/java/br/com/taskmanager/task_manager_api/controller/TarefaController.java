package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.controller.dto.request.TarefaCreateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.request.TarefaUpdateStatusRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.TarefaResponseDTO;
import br.com.taskmanager.task_manager_api.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService tarefaService;

    public TarefaController(TarefaService tarefaService) {
        this.tarefaService = tarefaService;
    }

    // ======================
    // CRIAR TAREFA
    // ======================
    @PostMapping
    public TarefaResponseDTO criar(
            @RequestBody @Valid TarefaCreateRequestDTO dto) {

        return tarefaService.criar(dto);
    }

    // ======================
    // LISTAR POR PROJETO
    // ======================    
    @GetMapping("/projeto/{projetoId}")
    public Page<TarefaResponseDTO> listarPorProjeto(
            @PathVariable Long projetoId,
            @PageableDefault(
                size = 10,
                sort = "dataCriacao"
            ) Pageable pageable) {
    
        return tarefaService.listarPorProjeto(projetoId, pageable);
    }
    

    // ======================
    // MOVER STATUS
    // ======================
    @PutMapping("/{id}/status")
    public TarefaResponseDTO moverStatus(
            @PathVariable Long id,
            @RequestBody @Valid TarefaUpdateStatusRequestDTO dto) {

        return tarefaService.moverStatus(id, dto.getStatus());
    }
}
