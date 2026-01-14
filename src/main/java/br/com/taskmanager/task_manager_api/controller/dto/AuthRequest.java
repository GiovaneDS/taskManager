package br.com.taskmanager.task_manager_api.controller.dto;

public class AuthRequest {

    private String username;
    private String password;

    public AuthRequest() {
        // construtor vazio obrigatório para o Jackson
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
