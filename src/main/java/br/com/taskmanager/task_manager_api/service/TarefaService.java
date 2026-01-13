package br.com.taskmanager.task_manager_api.service;

import br.com.taskmanager.task_manager_api.domain.entity.HistoricoTarefa;
import br.com.taskmanager.task_manager_api.domain.entity.Tarefa;
import br.com.taskmanager.task_manager_api.domain.entity.Usuario;
import br.com.taskmanager.task_manager_api.domain.enums.StatusTarefa;
import br.com.taskmanager.task_manager_api.domain.repository.HistoricoTarefaRepository;
import br.com.taskmanager.task_manager_api.domain.repository.TarefaRepository;
import br.com.taskmanager.task_manager_api.domain.repository.UsuarioRepository;
import br.com.taskmanager.task_manager_api.security.SecurityUtils;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository tarefaRepository;
    private final HistoricoTarefaRepository historicoRepository;
    private final UsuarioRepository usuarioRepository;    


    public TarefaService(
            TarefaRepository tarefaRepository,
            HistoricoTarefaRepository historicoRepository,
            UsuarioRepository usuarioRepository) {

        this.tarefaRepository = tarefaRepository;
        this.historicoRepository = historicoRepository;
        this.usuarioRepository = usuarioRepository;
    }


    public Tarefa criar(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    public List<Tarefa> listarPorProjeto(Long projetoId) {
        return tarefaRepository.findByProjetoId(projetoId);
    }

    public Tarefa moverStatus(Long tarefaId, StatusTarefa novoStatus) {

        String username = SecurityUtils.getUsernameLogado();

        Usuario usuario = usuarioRepository.findByEmail(username)
                .orElse(null);

        Tarefa tarefa = tarefaRepository.findById(tarefaId)
                .orElseThrow(() -> new RuntimeException("Tarefa não encontrada"));

        StatusTarefa statusAnterior = tarefa.getStatus();

        if (novoStatus == StatusTarefa.CONCLUIDA) {
            tarefa.setDataConclusao(LocalDateTime.now());
        }

        tarefa.setStatus(novoStatus);
        tarefaRepository.save(tarefa);

        // REGISTRA HISTÓRICO
        HistoricoTarefa historico = new HistoricoTarefa();
        historico.setTarefa(tarefa);
        historico.setStatusAnterior(statusAnterior);
        historico.setStatusNovo(novoStatus);

        // Por enquanto usuário nulo (login vem depois)
        historico.setUsuario(usuario);

        historicoRepository.save(historico);

        return tarefa;
    }
}
