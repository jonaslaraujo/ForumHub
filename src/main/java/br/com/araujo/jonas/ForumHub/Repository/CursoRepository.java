package br.com.araujo.jonas.ForumHub.Repository;

import br.com.araujo.jonas.ForumHub.Domain.CursoDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<CursoDomain, Long> {
}
