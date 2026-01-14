package br.com.taskmanager.task_manager_api.domain.repository;

import br.com.taskmanager.task_manager_api.domain.entity.HistoricoTarefa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface HistoricoTarefaRepository extends JpaRepository<HistoricoTarefa, Long> {

    Page<HistoricoTarefa> findByTarefaId(Long tarefaId, Pageable pageable);
}
