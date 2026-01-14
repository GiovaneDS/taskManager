package br.com.taskmanager.task_manager_api.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.taskmanager.task_manager_api.controller.dto.response.HistoricoTarefaResponseDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.ProjetoResponseDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.TarefaResponseDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.UsuarioResponseDTO;
import br.com.taskmanager.task_manager_api.domain.entity.HistoricoTarefa;
import br.com.taskmanager.task_manager_api.domain.entity.Tarefa;
import br.com.taskmanager.task_manager_api.domain.entity.Usuario;
import br.com.taskmanager.task_manager_api.domain.repository.HistoricoTarefaRepository;

@Service
public class HistoricoTarefaService {

    private final HistoricoTarefaRepository historicoRepository;

    public HistoricoTarefaService(HistoricoTarefaRepository historicoRepository) {
        this.historicoRepository = historicoRepository;
    }

    public Page<HistoricoTarefaResponseDTO> buscarHistoricoPorTarefa(
            Long tarefaId,
            Pageable pageable) {

        return historicoRepository
            .findByTarefaId(tarefaId, pageable)
            .map(this::mapToDTO);
    }

    // ==========================
    // MAPEAMENTOS
    // ==========================

    private HistoricoTarefaResponseDTO mapToDTO(HistoricoTarefa h) {
        return new HistoricoTarefaResponseDTO(
            h.getId(),
            mapTarefa(h.getTarefa()),
            mapUsuario(h.getUsuario()),
            h.getStatusAnterior().name(),
            h.getStatusNovo().name(),
            h.getDataEvento()
        );
    }

    private UsuarioResponseDTO mapUsuario(Usuario usuario) {
        if (usuario == null) return null;

        return new UsuarioResponseDTO(
            usuario.getId(),
            usuario.getNome(),
            usuario.getEmail(),
            usuario.isAtivo()
        );
    }

    private TarefaResponseDTO mapTarefa(Tarefa tarefa) {
        return new TarefaResponseDTO(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getDescricao(),
            tarefa.getStatus().name(),
            new ProjetoResponseDTO(
                tarefa.getProjeto().getId(),
                tarefa.getProjeto().getNome(),
                tarefa.getProjeto().getDescricao()
            ),
            mapUsuario(tarefa.getResponsavel()),
            tarefa.getDataCriacao(),
            tarefa.getDataConclusao()
        );
    }
}
