package br.com.alura.forumhub.forumhub.domain.topico;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TopicoService {

    private final TopicoRepository topicoRepository;

    // Metodo para fechar um tópico
    @Transactional
    public Topico fecharTopico(Long id) {
        Topico topico = buscarTopicoPorId(id);
        topico.setEstado(EstadoTopico.fechado);
        return topico;
    }

    // Metodo para arquivar um tópico
    @Transactional
    public Topico arquivarTopico(Long id) {
        Topico topico = buscarTopicoPorId(id);
        topico.setEstado(EstadoTopico.arquivado);
        return topico;
    }

    // Método auxiliar para buscar um tópico por ID
    private Topico buscarTopicoPorId(Long id) {
        Optional<Topico> optionalTopico = topicoRepository.findById(id);
        if (optionalTopico.isEmpty()) {
            throw new RuntimeException("Tópico não encontrado: " + id);
        }
        return optionalTopico.get();
    }
}
