package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.domain.entity.Projeto;
import br.com.taskmanager.task_manager_api.domain.repository.ProjetoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projetos")
public class ProjetoController {

    private final ProjetoRepository repository;

    public ProjetoController(ProjetoRepository repository) {
        this.repository = repository;
    }

    // GET /api/projetos
    @GetMapping
    public List<Projeto> listar() {
        return repository.findAll();
    }

    // POST /api/projetos
    @PostMapping
    public Projeto criar(@RequestBody Projeto projeto) {
        return repository.save(projeto);
    }
}
