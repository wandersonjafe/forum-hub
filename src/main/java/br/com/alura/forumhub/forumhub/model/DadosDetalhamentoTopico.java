package br.com.alura.forumhub.forumhub.model;
import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        String titulo,
        String mensagem,
        LocalDateTime dataCriacao,
        String estado,
        String autor,
        String curso
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getDataCriacao(),
                topico.getEstado().name(),
                topico.getAutor().getNome(),
                topico.getCurso().getNome()
        );
    }
}
