package br.com.alura.forumhub.forumhub.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "topico")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String mensagem;

    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private br.com.alura.forumhub.forumhub.model.EstadoTopico estado = br.com.alura.forumhub.forumhub.model.EstadoTopico.aberto;

    @ManyToOne
    private Usuario autor;

    @ManyToOne
    private Curso curso;

    // Construtor manual com entidades já resolvidas
    public Topico(String titulo, String mensagem, Usuario autor, Curso curso) {
        this.titulo = titulo;
        this.mensagem = mensagem;
        this.autor = autor;
        this.curso = curso;
        this.estado = br.com.alura.forumhub.forumhub.model.EstadoTopico.aberto;
        this.dataCriacao = LocalDateTime.now();
    }

    // Métodos de mudança de estado
    public void fechar() {
        this.estado = br.com.alura.forumhub.forumhub.model.EstadoTopico.fechado;
    }

    public void arquivar() {
        this.estado = br.com.alura.forumhub.forumhub.model.EstadoTopico.arquivado;
    }

    // Atualização condicional
    public void atualizarInformacoes(DadosAtualizacaoTopico dados) {
        if (dados.titulo() != null) {
            this.titulo = dados.titulo();
        }
        if (dados.mensagem() != null) {
            this.mensagem = dados.mensagem();
        }
        if (dados.estado() != null) {
            this.estado = dados.estado(); // agora é do tipo correto
        }
    }
}
