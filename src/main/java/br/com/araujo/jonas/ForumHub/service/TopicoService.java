package br.com.araujo.jonas.ForumHub.service;

import br.com.araujo.jonas.ForumHub.domain.TopicoDomain;
import br.com.araujo.jonas.ForumHub.http.request.CriarTopicoRequest;
import br.com.araujo.jonas.ForumHub.infra.exception.DataAlreadyRegisteredException;
import br.com.araujo.jonas.ForumHub.model.DetalheTopico;
import br.com.araujo.jonas.ForumHub.repository.CursoRepository;
import br.com.araujo.jonas.ForumHub.repository.PerfilRepository;
import br.com.araujo.jonas.ForumHub.repository.TopicoRepository;
import br.com.araujo.jonas.ForumHub.repository.UsuarioRepository;
import jakarta.persistence.NoResultException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoService {
    private final UsuarioRepository usuarioRepository;
    private final CursoRepository cursoRepository;
    private final PerfilRepository perfilRepository;
    private final TopicoRepository repository;
    private final String TOPICO_NAO_LOCALIZADO = "Tópico não localizado.";
    private final String AUTOR_NAO_LOCALIZADO = "Autor não localizado.";
    private final String CURSO_NAO_LOCALIZADO = "Curso não localizado.";

    public TopicoDomain criar(CriarTopicoRequest request) {
        validarJaCadastradoTituloMensagem(request);

        var perfil = perfilRepository.findByName("Jonas")
                .orElseThrow(() -> new NoResultException("Não encontrei"));


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

    // Método para listar todos os tópicos
    public List<TopicoDomain> listarTodos() {
        return repository.findAll();
    }

    // Método para listar tópicos de forma paginada, 10 por página, ordenado por dataCriacao ASC
    public Page<TopicoDomain> listarPaginado(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("dataCriacao").ascending());
        return repository.findAll(pageable);
    }

    // Método para listar tópicos por nome de curso e ano específico
    public Page<TopicoDomain> listarPorCursoEAno(String nomeCurso, int ano, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return repository.findByCursoNomeAndAno(nomeCurso, ano, pageable);
    }

    public DetalheTopico listarDetalheTopico(Long id) {
        var topico = repository.findById(id);

        if (!topico.isPresent()) {
            throw new NoResultException("Tópico não encontrado.");
        }
        return new DetalheTopico(topico.get(), "Detalhes do tópico");
    }

    private void validarJaCadastradoTituloMensagem(CriarTopicoRequest request) {
        if (repository.findByTituloAndMensagem(request.getTitulo(), request.getMensagem())) {
            throw new DataAlreadyRegisteredException("Tópico já cadastrado");
        }
    }

}
