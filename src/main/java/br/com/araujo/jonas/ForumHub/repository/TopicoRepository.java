package br.com.araujo.jonas.ForumHub.repository;

import br.com.araujo.jonas.ForumHub.domain.TopicoDomain;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface TopicoRepository extends JpaRepository<TopicoDomain, Long> {

    Boolean findByTituloAndMensagem(String titulo, String mensagem);

    // Método para listar tudo paginado e ordenado por dataCriacao ASC
    Page<TopicoDomain> findAllByOrderByDataCriacaoAsc(Pageable pageable);

    // Método para listar por nome de curso e ano específico
    @Query(value = "SELECT " +
            "   t " +
            "FROM " +
            "   TopicoDomain t " +
            "   join CursoDomain c on " +
            "       t.curso = c.id " +
            "WHERE " +
            "   c.nome = :nome " +
            "   AND YEAR(t.dataCriacao) = :ano", nativeQuery = true)
    Page<TopicoDomain> findByCursoNomeAndAno(@Param("nome") String nomeCurso, @Param("ano") int ano, Pageable pageable);
}
