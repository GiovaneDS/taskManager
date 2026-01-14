package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.controller.dto.response.HistoricoTarefaResponseDTO;
import br.com.taskmanager.task_manager_api.service.HistoricoTarefaService;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequestMapping("/api/tarefas/{tarefaId}/historico")
public class HistoricoTarefaController {

    private final HistoricoTarefaService historicoTarefaService;

    public HistoricoTarefaController(HistoricoTarefaService historicoTarefaService) {
        this.historicoTarefaService = historicoTarefaService;
    }

    @GetMapping("/api/tarefas/{tarefaId}/historico")
    public Page<HistoricoTarefaResponseDTO> listarHistorico(
            @PathVariable Long tarefaId,
            Pageable pageable) {
    
        return historicoTarefaService
            .buscarHistoricoPorTarefa(tarefaId, pageable);
    }
    
}
