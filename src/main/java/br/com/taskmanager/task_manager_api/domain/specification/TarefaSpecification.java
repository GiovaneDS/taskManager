package br.com.taskmanager.task_manager_api.domain.specification;

import br.com.taskmanager.task_manager_api.domain.entity.Tarefa;
import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;
import org.springframework.data.jpa.domain.Specification;

public class TarefaSpecification {

    public static Specification<Tarefa> porProjeto(Long projetoId) {
        return (root, query, cb) ->
            projetoId == null ? null :
                cb.equal(root.get("projeto").get("id"), projetoId);
    }

    public static Specification<Tarefa> porStatus(StatusTarefa status) {
        return (root, query, cb) ->
            status == null ? null :
                cb.equal(root.get("status"), status);
    }

    public static Specification<Tarefa> porResponsavel(Long responsavelId) {
        return (root, query, cb) ->
            responsavelId == null ? null :
                cb.equal(root.get("responsavel").get("id"), responsavelId);
    }
}
