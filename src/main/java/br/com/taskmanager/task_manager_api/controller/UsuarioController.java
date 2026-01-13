package br.com.taskmanager.task_manager_api.controller;

import br.com.taskmanager.task_manager_api.domain.entity.Usuario;
import br.com.taskmanager.task_manager_api.domain.repository.UsuarioRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioRepository repository;

    // Construtor usado pelo Spring para injeção de dependência
    public UsuarioController(UsuarioRepository repository) {
        this.repository = repository;
    }

    // GET http://localhost:8081/api/usuarios
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll();
    }

    // POST http://localhost:8081/api/usuarios
    @PostMapping
    public Usuario criar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }
}
