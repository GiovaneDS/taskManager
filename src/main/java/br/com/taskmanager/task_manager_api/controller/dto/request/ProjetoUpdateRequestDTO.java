package br.com.taskmanager.task_manager_api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ProjetoUpdateRequestDTO {

    @NotBlank
    private String nome;

    private String descricao;

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
