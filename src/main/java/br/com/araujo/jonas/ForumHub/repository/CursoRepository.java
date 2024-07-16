package br.com.araujo.jonas.ForumHub.repository;

import br.com.araujo.jonas.ForumHub.domain.CursoDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoDomain, Long> {
}
