package br.com.alura.forumhub.forumhub.repository;

import br.com.alura.forumhub.forumhub.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
