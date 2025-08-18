package br.com.alura.forumhub.forumhub.repository;

import br.com.alura.forumhub.forumhub.model.Topico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface TopicoRepository extends JpaRepository<Topico, Long> {
    boolean existsByTituloAndMensagem(String titulo, String mensagem);
    Optional<Topico> findByTituloAndMensagem(String titulo, String mensagem);

    // Aqui adicionamos o metodo para corrigir os enums
    @Modifying
    @Transactional
    @Query("UPDATE Topico t SET t.estado = 'ABERTO' WHERE t.estado = 'aberto'")
    void atualizarEstadosAberto();

    @Modifying
    @Transactional
    @Query("UPDATE Topico t SET t.estado = 'FECHADO' WHERE t.estado = 'fechado'")
    void atualizarEstadosFechado();

    @Modifying
    @Transactional
    @Query("UPDATE Topico t SET t.estado = 'ARQUIVADO' WHERE t.estado = 'arquivado'")
    void atualizarEstadosArquivado();
}
