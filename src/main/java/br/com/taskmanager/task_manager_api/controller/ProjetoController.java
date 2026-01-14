package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.controller.dto.request.ProjetoCreateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.request.ProjetoUpdateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.ProjetoResponseDTO;
import br.com.taskmanager.task_manager_api.service.ProjetoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoService projetoService;

    public ProjetoController(ProjetoService projetoService) {
        this.projetoService = projetoService;
    }

    @PostMapping
    public ProjetoResponseDTO criar(
            @RequestBody @Valid ProjetoCreateRequestDTO dto) {

        return projetoService.criar(dto);
    }

    @PutMapping("/{id}")
    public ProjetoResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid ProjetoUpdateRequestDTO dto) {

        return projetoService.atualizar(id, dto);
    }

}
