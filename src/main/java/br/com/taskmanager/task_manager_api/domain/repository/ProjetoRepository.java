package br.com.taskmanager.task_manager_api.domain.repository;

import br.com.taskmanager.task_manager_api.domain.entity.Projeto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjetoRepository extends JpaRepository<Projeto, Long> {
}
