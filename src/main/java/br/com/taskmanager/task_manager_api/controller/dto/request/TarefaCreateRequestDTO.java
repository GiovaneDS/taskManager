package br.com.taskmanager.task_manager_api.controller.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class TarefaCreateRequestDTO {

    @NotBlank
    private String titulo;

    private String descricao;

    @NotNull
    private Long projetoId;

    private Long responsavelId;

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public Long getProjetoId() {
        return projetoId;
    }

    public Long getResponsavelId() {
        return responsavelId;
    }
}
