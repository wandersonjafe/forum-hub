package br.com.alura.forumhub.forumhub.model;

public record DadosAtualizacaoTopico(
        String titulo,
        String mensagem,
        EstadoTopico estado
) {}
