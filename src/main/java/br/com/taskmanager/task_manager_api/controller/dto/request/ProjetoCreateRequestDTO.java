package br.com.taskmanager.task_manager_api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;

public class ProjetoCreateRequestDTO {

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
