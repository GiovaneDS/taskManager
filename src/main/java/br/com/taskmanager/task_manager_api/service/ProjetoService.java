package br.com.taskmanager.task_manager_api.service;

import br.com.taskmanager.task_manager_api.controller.dto.request.ProjetoCreateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.request.ProjetoUpdateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.ProjetoResponseDTO;
import br.com.taskmanager.task_manager_api.domain.entity.Projeto;
import br.com.taskmanager.task_manager_api.domain.repository.ProjetoRepository;
import br.com.taskmanager.task_manager_api.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProjetoService {

    private final ProjetoRepository projetoRepository;

    public ProjetoService(ProjetoRepository projetoRepository) {
        this.projetoRepository = projetoRepository;
    }

    public ProjetoResponseDTO criar(ProjetoCreateRequestDTO dto) {

        Projeto projeto = new Projeto();
        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());

        Projeto salvo = projetoRepository.save(projeto);

        return new ProjetoResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getDescricao()
        );
    }

    public ProjetoResponseDTO atualizar(
            Long id,
            ProjetoUpdateRequestDTO dto) {

        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Projeto não encontrado"));

        projeto.setNome(dto.getNome());
        projeto.setDescricao(dto.getDescricao());

        Projeto salvo = projetoRepository.save(projeto);

        return new ProjetoResponseDTO(
            salvo.getId(),
            salvo.getNome(),
            salvo.getDescricao()
        );
    }

    public List<ProjetoResponseDTO> listar() {
        return projetoRepository.findAll()
            .stream()
            .map(p -> new ProjetoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getDescricao()
            ))
            .toList();
    }

    public void deletar(Long id) {

        Projeto projeto = projetoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Projeto não encontrado"));
    
        projetoRepository.delete(projeto);
    }
    

}
