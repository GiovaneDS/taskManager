package br.com.taskmanager.task_manager_api.domain.repository;

import br.com.taskmanager.task_manager_api.domain.entity.HistoricoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoTarefaRepository extends JpaRepository<HistoricoTarefa, Long> {

    List<HistoricoTarefa> findByTarefaIdOrderByDataEventoAsc(Long tarefaId);
}
