package br.com.taskmanager.task_manager_api.service;

import br.com.taskmanager.task_manager_api.controller.dto.request.TarefaCreateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.TarefaResponseDTO;
import br.com.taskmanager.task_manager_api.domain.entity.HistoricoTarefa;
import br.com.taskmanager.task_manager_api.domain.entity.Projeto;
import br.com.taskmanager.task_manager_api.domain.entity.Tarefa;
import br.com.taskmanager.task_manager_api.domain.entity.Usuario;
import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;
import br.com.taskmanager.task_manager_api.domain.repository.HistoricoTarefaRepository;
import br.com.taskmanager.task_manager_api.domain.repository.ProjetoRepository;
import br.com.taskmanager.task_manager_api.domain.repository.TarefaRepository;
import br.com.taskmanager.task_manager_api.domain.repository.UsuarioRepository;
import br.com.taskmanager.task_manager_api.security.SecurityUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final HistoricoTarefaRepository historicoRepository;
    private final UsuarioRepository usuarioRepository; 
    private final ProjetoRepository projetoRepository;   


    public TarefaService(
            TarefaRepository tarefaRepository,
            HistoricoTarefaRepository historicoRepository,
            UsuarioRepository usuarioRepository,
            ProjetoRepository projetoRepository) {

        this.tarefaRepository = tarefaRepository;
        this.historicoRepository = historicoRepository;
        this.usuarioRepository = usuarioRepository;
        this.projetoRepository = projetoRepository;
    }


    public TarefaResponseDTO criar(TarefaCreateRequestDTO dto) {

        Projeto projeto = projetoRepository.findById(dto.getProjetoId())
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));

        Usuario responsavel = null;
        if (dto.getResponsavelId() != null) {
            responsavel = usuarioRepository.findById(dto.getResponsavelId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        }

        Tarefa tarefa = new Tarefa();
        tarefa.setTitulo(dto.getTitulo());
        tarefa.setDescricao(dto.getDescricao());
        tarefa.setProjeto(projeto);
        tarefa.setResponsavel(responsavel);
        tarefa.setStatus(StatusTarefa.BACKLOG);
        tarefa.setDataCriacao(LocalDateTime.now());

        Tarefa salva = tarefaRepository.save(tarefa);

        return mapToResponseDTO(salva);
    }

    // =========================
    // LIST
    // =========================
    public Page<TarefaResponseDTO> listarPorProjeto(
            Long projetoId,
            Pageable pageable) {

        return tarefaRepository
            .findByProjetoId(projetoId, pageable)
            .map(this::mapToResponseDTO);
    }

    // =========================
    // STATUS
    // =========================
    public TarefaResponseDTO moverStatus(Long tarefaId, StatusTarefa novoStatus) {

        String username = SecurityUtils.getUsernameLogado();

        Usuario usuario = usuarioRepository.findByEmail(username)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
            .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        StatusTarefa statusAnterior = tarefa.getStatus();

        if (novoStatus == StatusTarefa.CONCLUIDA) {
            tarefa.setDataConclusao(LocalDateTime.now());
        }

        tarefa.setStatus(novoStatus);
        tarefaRepository.save(tarefa);

        HistoricoTarefa historico = new HistoricoTarefa();
        historico.setTarefa(tarefa);
        historico.setStatusAnterior(statusAnterior);
        historico.setStatusNovo(novoStatus);
        historico.setUsuario(usuario);

        historicoRepository.save(historico);

        return mapToResponseDTO(tarefa);
    }

    // =========================
    // MAPPER
    // =========================
    private TarefaResponseDTO mapToResponseDTO(Tarefa tarefa) {
        return new TarefaResponseDTO(
            tarefa.getId(),
            tarefa.getTitulo(),
            tarefa.getDescricao(),
            tarefa.getStatus().name(),
            null, // projeto DTO (se quiser, extraímos)
            null, // responsável DTO (idem)
            tarefa.getDataCriacao(),
            tarefa.getDataConclusao()
        );
    }
}