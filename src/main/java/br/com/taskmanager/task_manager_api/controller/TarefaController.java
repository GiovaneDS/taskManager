package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.controller.dto.request.TarefaCreateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.request.TarefaUpdateStatusRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.TarefaResponseDTO;
import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;
import br.com.taskmanager.task_manager_api.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.access.prepost.PreAuthorize;

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
    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public TarefaResponseDTO criar(
            @RequestBody @Valid TarefaCreateRequestDTO dto) {

        return tarefaService.criar(dto);
    }

    // ======================
    // LISTAR POR PROJETO
    // ======================    
    @PreAuthorize("hasRole('USER')")
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
    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{id}/status")
    public TarefaResponseDTO moverStatus(
            @PathVariable Long id,
            @RequestBody @Valid TarefaUpdateStatusRequestDTO dto) {

        return tarefaService.moverStatus(id, dto.getStatus());
    }

    @GetMapping
    public Page<TarefaResponseDTO> filtrar(
            @RequestParam(required = false) Long projetoId,
            @RequestParam(required = false) StatusTarefa status,
            @RequestParam(required = false) Long responsavelId,
            Pageable pageable) {

        return tarefaService.filtrar(
                projetoId,
                status,
                responsavelId,
                pageable);
    }

}
