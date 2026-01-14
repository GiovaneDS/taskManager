package br.com.taskmanager.task_manager_api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public class UsuarioUpdateRequestDTO {

    @NotBlank
    private String nome;

    private boolean ativo;

    public String getNome() {
        return nome;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
