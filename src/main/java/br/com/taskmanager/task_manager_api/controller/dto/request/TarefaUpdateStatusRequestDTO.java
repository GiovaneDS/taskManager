package br.com.taskmanager.task_manager_api.controller.dto.request;

import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;
import jakarta.validation.constraints.NotNull;

public class TarefaUpdateStatusRequestDTO {

    @NotNull
    private StatusTarefa status;

    public StatusTarefa getStatus() {
        return status;
    }
}
