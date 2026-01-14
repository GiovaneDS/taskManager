package br.com.taskmanager.task_manager_api.service;

import br.com.taskmanager.task_manager_api.controller.dto.request.UsuarioCreateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.request.UsuarioUpdateRequestDTO;
import br.com.taskmanager.task_manager_api.controller.dto.response.UsuarioResponseDTO;
import br.com.taskmanager.task_manager_api.domain.entity.Usuario;
import br.com.taskmanager.task_manager_api.domain.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO criarUsuario(UsuarioCreateRequestDTO dto) {

        Usuario usuario = new Usuario();
        usuario.setNome(dto.getNome());
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setAtivo(true);

        Usuario salvo = usuarioRepository.save(usuario);

        return new UsuarioResponseDTO(
                salvo.getId(),
                salvo.getNome(),
                salvo.getEmail(),
                salvo.isAtivo()
        );
    }

    public UsuarioResponseDTO atualizar(
        Long id,
        UsuarioUpdateRequestDTO dto) {

    Usuario usuario = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    usuario.setNome(dto.getNome());
    usuario.setAtivo(dto.isAtivo());

    Usuario salvo = usuarioRepository.save(usuario);

    return new UsuarioResponseDTO(
        salvo.getId(),
        salvo.getNome(),
        salvo.getEmail(),
        salvo.isAtivo()
    );
}
}
