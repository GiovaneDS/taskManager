package br.com.taskmanager.task_manager_api.domain.repository;


import br.com.taskmanager.task_manager_api.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

}
