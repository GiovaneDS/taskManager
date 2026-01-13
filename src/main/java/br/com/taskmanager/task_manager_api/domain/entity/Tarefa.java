package br.com.taskmanager.task_manager_api.domain.entity;

import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "tarefas")
@Data
public class Tarefa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatusTarefa status;

    @ManyToOne
    @JoinColumn(name = "projeto_id", nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "responsavel_id")
    private Usuario responsavel;

    private LocalDateTime dataCriacao;

    private LocalDateTime dataConclusao;

    @PrePersist
    public void aoCriar() {
        this.dataCriacao = LocalDateTime.now();
        this.status = StatusTarefa.BACKLOG;
    }
}
