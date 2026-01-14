package br.com.taskmanager.task_manager_api.controller.dto.response;

import java.time.LocalDateTime;

public class TarefaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String status;
    private ProjetoResponseDTO projeto;
    private UsuarioResponseDTO responsavel;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataConclusao;

    public TarefaResponseDTO(
            Long id,
            String titulo,
            String descricao,
            String status,
            ProjetoResponseDTO projeto,
            UsuarioResponseDTO responsavel,
            LocalDateTime dataCriacao,
            LocalDateTime dataConclusao
    ) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.projeto = projeto;
        this.responsavel = responsavel;
        this.dataCriacao = dataCriacao;
        this.dataConclusao = dataConclusao;
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getStatus() {
        return status;
    }

    public ProjetoResponseDTO getProjeto() {
        return projeto;
    }

    public UsuarioResponseDTO getResponsavel() {
        return responsavel;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public LocalDateTime getDataConclusao() {
        return dataConclusao;
    }
}
