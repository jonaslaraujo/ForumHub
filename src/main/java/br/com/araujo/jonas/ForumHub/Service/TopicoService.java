package br.com.araujo.jonas.ForumHub.Service;

import br.com.araujo.jonas.ForumHub.Domain.TopicoDomain;
import br.com.araujo.jonas.ForumHub.Http.Request.CriarTopicoRequest;
import br.com.araujo.jonas.ForumHub.Infra.DataAlreadyRegisteredException;
import br.com.araujo.jonas.ForumHub.Repository.CursoRepository;
import br.com.araujo.jonas.ForumHub.Repository.TopicoRepository;
import br.com.araujo.jonas.ForumHub.Repository.UsuarioRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final TopicoRepository repository;

    public TopicoDomain criar(CriarTopicoRequest request) {
        validarJaCadastrado(request);

        var autor = usuarioRepository.findById(request.getAutor())
                .orElseThrow(() -> new NoResultException("Autor não localizado."));

        var curso = cursoRepository.findById(request.getCurso())
                .orElseThrow(() -> new NoResultException("Curso não localizado"));

        var topico = new TopicoDomain();
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setMensagem(request.getMensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setTitulo(request.getTitulo());
        return repository.save(topico);

    }

    private void validarJaCadastrado(CriarTopicoRequest request) {
        if (repository.findByTituloAndMensagem(request.getTitulo(), request.getMensagem())) {
            throw new DataAlreadyRegisteredException("Tópico já cadastrado");
        }
    }

}
