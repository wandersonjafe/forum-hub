package br.com.alura.forumhub.forumhub.dto.topico;

import br.com.alura.forumhub.forumhub.domain.topico.EstadoTopico;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        EstadoTopico estado
) {}
