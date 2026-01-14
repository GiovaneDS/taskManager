package br.com.taskmanager.task_manager_api.controller.dto.response;

import java.time.LocalDateTime;

public class HistoricoTarefaResponseDTO {

    private Long id;
    private TarefaResponseDTO tarefa;
    private UsuarioResponseDTO usuario;
    private String statusAnterior;
    private String statusNovo;
    private LocalDateTime dataEvento;

    public HistoricoTarefaResponseDTO(
            Long id,
            TarefaResponseDTO tarefa,
            UsuarioResponseDTO usuario,
            String statusAnterior,
            String statusNovo,
            LocalDateTime dataEvento
    ) {
        this.id = id;
        this.tarefa = tarefa;
        this.usuario = usuario;
        this.statusAnterior = statusAnterior;
        this.statusNovo = statusNovo;
        this.dataEvento = dataEvento;
    }

    public Long getId() {
        return id;
    }

    public TarefaResponseDTO getTarefa() {
        return tarefa;
    }

    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public String getStatusAnterior() {
        return statusAnterior;
    }

    public String getStatusNovo() {
        return statusNovo;
    }

    public LocalDateTime getDataEvento() {
        return dataEvento;
    }
}
