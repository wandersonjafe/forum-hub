package br.com.alura.forumhub.forumhub.repository;

import br.com.alura.forumhub.forumhub.model.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long> {
}
