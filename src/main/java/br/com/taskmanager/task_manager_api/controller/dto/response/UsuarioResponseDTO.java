package br.com.taskmanager.task_manager_api.controller.dto.response;

public class UsuarioResponseDTO {

    private Long id;
    private String nome;
    private String email;
    private boolean ativo;

    public UsuarioResponseDTO(Long id, String nome, String email, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.ativo = ativo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAtivo() {
        return ativo;
    }
}
