package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.controller.dto.request.UsuarioCreateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.request.UsuarioUpdateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.UsuarioResponseDTO;
import br.com.taskmanager.task_manager_api.service.UsuarioService;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioResponseDTO criar(
            @RequestBody @Valid UsuarioCreateRequestDTO dto) {

        return usuarioService.criarUsuario(dto);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(
            @PathVariable Long id,
            @RequestBody @Valid UsuarioUpdateRequestDTO dto) {

        return usuarioService.atualizar(id, dto);
    }

}
