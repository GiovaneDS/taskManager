package br.com.taskmanager.task_manager_api.domain.repository;

import br.com.taskmanager.task_manager_api.domain.entity.Tarefa;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TarefaRepository extends JpaRepository<Tarefa, Long>, JpaSpecificationExecutor<Tarefa> {

    Page<Tarefa> findByProjetoId(Long projetoId, Pageable pageable);
}
