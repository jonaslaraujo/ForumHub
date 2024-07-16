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
    private final String TOPICO_NAO_LOCALIZADO = "Tópico não localizado.";
    private final String AUTOR_NAO_LOCALIZADO = "Autor não localizado.";
    private final String CURSO_NAO_LOCALIZADO = "Curso não localizado.";

    public TopicoDomain criar(CriarTopicoRequest request) {
        validarJaCadastradoTituloMensagem(request);

        var autor = usuarioRepository.findById(request.getAutor())
                .orElseThrow(() -> new NoResultException(AUTOR_NAO_LOCALIZADO));

        var curso = cursoRepository.findById(request.getCurso())
                .orElseThrow(() -> new NoResultException(CURSO_NAO_LOCALIZADO));

        var topico = new TopicoDomain();
        topico.setAutor(autor);
        topico.setCurso(curso);
        topico.setMensagem(request.getMensagem());
        topico.setDataCriacao(LocalDateTime.now());
        topico.setTitulo(request.getTitulo());
        return repository.save(topico);

    }

    public TopicoDomain atualizar(Long id, CriarTopicoRequest request) {
        var topico = repository.findById(id)
                .orElseThrow(() -> new NoResultException(TOPICO_NAO_LOCALIZADO));

        validarJaCadastradoTituloMensagem(request);

        var autor = usuarioRepository.findById(request.getAutor())
                .orElseThrow(() -> new NoResultException(AUTOR_NAO_LOCALIZADO));

        var curso = cursoRepository.findById(request.getCurso())
                .orElseThrow(() -> new NoResultException(CURSO_NAO_LOCALIZADO));

        topico.setTitulo(request.getTitulo());
        topico.setMensagem(request.getMensagem());
        topico.setCurso(curso);
        return repository.save(topico);
    }

    public void excluir(Long id){
        var topico = repository.findById(id)
                .orElseThrow(() -> new NoResultException(TOPICO_NAO_LOCALIZADO));
        repository.delete(topico);
    }

    private void validarJaCadastradoTituloMensagem(CriarTopicoRequest request) {
        if (repository.findByTituloAndMensagem(request.getTitulo(), request.getMensagem())) {
            throw new DataAlreadyRegisteredException("Tópico já cadastrado");
        }
    }

}
