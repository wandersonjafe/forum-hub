package br.com.alura.forumhub.forumhub.controller;

import br.com.alura.forumhub.forumhub.dto.DadosTopico;
import br.com.alura.forumhub.forumhub.model.Topico;
import br.com.alura.forumhub.forumhub.model.Usuario;
import br.com.alura.forumhub.forumhub.model.Curso;
import br.com.alura.forumhub.forumhub.repository.TopicoRepository;
import br.com.alura.forumhub.forumhub.repository.UsuarioRepository;
import br.com.alura.forumhub.forumhub.repository.CursoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public void cadastrar(@RequestBody @Valid DadosTopico dados) {
        Usuario autor = usuarioRepository.findById(dados.idAutor())
                .orElseThrow(() -> new RuntimeException("Autor não encontrado"));

        Curso curso = cursoRepository.findById(dados.idCurso())
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));

        Topico topico = new Topico(dados.titulo(), dados.mensagem(), autor, curso);
        topicoRepository.save(topico);
    }
}
