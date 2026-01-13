package br.com.taskmanager.task_manager_api.domain.entity;

import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_tarefa")
@Data
public class HistoricoTarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Tarefa relacionada
    @ManyToOne
    @JoinColumn(name = "tarefa_id", nullable = false)
    private Tarefa tarefa;

    // Usuário que realizou a ação
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    // Status anterior
    @Enumerated(EnumType.STRING)
    @Column(name = "status_anterior")
    private StatusTarefa statusAnterior;

    // Novo status
    @Enumerated(EnumType.STRING)
    @Column(name = "status_novo")
    private StatusTarefa statusNovo;

    // Data do evento
    private LocalDateTime dataEvento;

    @PrePersist
    public void aoCriar() {
        this.dataEvento = LocalDateTime.now();
    }
}
