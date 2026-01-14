package br.com.taskmanager.task_manager_api.controller.dto.response;

public class ProjetoResponseDTO {

    private Long id;
    private String nome;
    private String descricao;

    public ProjetoResponseDTO(Long id, String nome, String descricao) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }
}
