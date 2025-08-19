package br.com.alura.forumhub.forumhub.dto.autenticacao;

import jakarta.validation.constraints.NotBlank;

public record DadosAutenticacao(

        @NotBlank String email,
        @NotBlank String senha) {
}
