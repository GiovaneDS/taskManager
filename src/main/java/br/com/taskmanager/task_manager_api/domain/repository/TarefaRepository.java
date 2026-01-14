package br.com.taskmanager.task_manager_api.domain.repository;

import br.com.taskmanager.task_manager_api.domain.entity.Tarefa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

    Page<Tarefa> findByProjetoId(Long projetoId, Pageable pageable);
}
