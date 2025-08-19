package br.com.alura.forumhub.forumhub.controller;

import br.com.alura.forumhub.forumhub.dto.autenticacao.DadosAutenticacao;
import br.com.alura.forumhub.forumhub.domain.usuario.Usuario;
import br.com.alura.forumhub.forumhub.dto.autenticacao.DadosTokenJWT;
import br.com.alura.forumhub.forumhub.infra.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        // Cria o token de autenticação com email e senha
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        // Valida o login no Spring Security
        var authentication = manager.authenticate(authenticationToken);

        // Gera o JWT com os dados do usuário autenticado
        var tokenJWT = tokenService.gerarToken((Usuario) authentication.getPrincipal());

        // Retorna o token para o cliente
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }
}
