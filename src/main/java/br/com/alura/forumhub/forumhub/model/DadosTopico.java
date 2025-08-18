package br.com.alura.forumhub.forumhub.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosTopico(
        @NotBlank(message = "O título é obrigatório")
        String titulo,

        @NotBlank(message = "A mensagem é obrigatória")
        String mensagem,

        @NotNull(message = "O id do autor é obrigatório")
        Long idAutor,

        @NotNull(message = "O id do curso é obrigatório")
        Long idCurso
) {}
