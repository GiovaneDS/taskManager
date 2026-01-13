package br.com.taskmanager.task_manager_api.controller.dto;

import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;

public class AlterarStatusTarefaDTO {

    private StatusTarefa status;

    public StatusTarefa getStatus() {
        return status;
    }

    public void setStatus(StatusTarefa status) {
        this.status = status;
    }
}
