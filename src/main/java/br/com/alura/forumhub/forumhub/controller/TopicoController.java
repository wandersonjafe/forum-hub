package br.com.alura.forumhub.forumhub.controller;

import br.com.alura.forumhub.forumhub.domain.curso.Curso;
import br.com.alura.forumhub.forumhub.domain.curso.CursoRepository;
import br.com.alura.forumhub.forumhub.domain.topico.*;
import br.com.alura.forumhub.forumhub.dto.topico.DadosAtualizacaoTopico;
import br.com.alura.forumhub.forumhub.dto.topico.DadosDetalhamentoTopico;
import br.com.alura.forumhub.forumhub.dto.topico.DadosListagemTopico;
import br.com.alura.forumhub.forumhub.dto.topico.DadosTopico;
import br.com.alura.forumhub.forumhub.domain.usuario.Usuario;
import br.com.alura.forumhub.forumhub.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping("/teste")
    public ResponseEntity<String> testeAutenticacao() {
        return ResponseEntity.ok("Acesso permitido: usuário autenticado!");
    }

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosTopico dados,
                                       UriComponentsBuilder uriBuilder) {

        Usuario autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));
        Curso curso = cursoRepository.findById(dados.idCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Optional<Topico> topicoExistente = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
        if (topicoExistente.isPresent()) {
            return ResponseEntity.badRequest()
                    .body("Já existe um tópico com esse título e mensagem!");
        }

        Topico topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);
        repository.save(topico);

        var uri = uriBuilder.path("/topicos/{id}")
                .buildAndExpand(topico.getId())
                .toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }

    // Listar os dados
    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = repository.findAll(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    // Detalhar os dados
    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoTopico> detalhar(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new DadosDetalhamentoTopico(optionalTopico.get()));
    }

    // Atualizar os dados
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizar(@PathVariable Long id,
                                       @RequestBody @Valid DadosAtualizacaoTopico dados) {
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var topico = optionalTopico.get();

        if (dados.titulo() != null && dados.mensagem() != null) {
            Optional<Topico> topicoExistente = repository.findByTituloAndMensagem(dados.titulo(), dados.mensagem());
            if (topicoExistente.isPresent() && !topicoExistente.get().getId().equals(id)) {
                return ResponseEntity.badRequest()
                        .body("Já existe um tópico com esse título e mensagem!");
            }
        }

        topico.atualizarInformacoes(dados);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    // Excluir
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        var optionalTopico = repository.findById(id);
        if (optionalTopico.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
