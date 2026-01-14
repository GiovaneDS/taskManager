package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.controller.dto.response.HistoricoTarefaResponseDTO;
import br.com.taskmanager.task_manager_api.service.HistoricoTarefaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas/{tarefaId}/historico")
public class HistoricoTarefaController {

    private final HistoricoTarefaService historicoTarefaService;

    public HistoricoTarefaController(HistoricoTarefaService historicoTarefaService) {
        this.historicoTarefaService = historicoTarefaService;
    }

    @GetMapping
    public List<HistoricoTarefaResponseDTO> listarHistorico(
            @PathVariable Long tarefaId) {

        return historicoTarefaService.buscarHistoricoPorTarefa(tarefaId);
    }
}
