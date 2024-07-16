package br.com.araujo.jonas.ForumHub.Repository;

import br.com.araujo.jonas.ForumHub.Domain.RespostaDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RespostaRepository extends JpaRepository<RespostaDomain, Long> {
}
