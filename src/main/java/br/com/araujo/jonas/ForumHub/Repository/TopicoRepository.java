package br.com.araujo.jonas.ForumHub.Repository;

import br.com.araujo.jonas.ForumHub.Domain.TopicoDomain;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<TopicoDomain, Long> {

    Boolean findByTituloAndMensagem(String titulo, String mensagem);
}
