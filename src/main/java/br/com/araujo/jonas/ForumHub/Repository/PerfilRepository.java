package br.com.araujo.jonas.ForumHub.Repository;

import br.com.araujo.jonas.ForumHub.Domain.PerfilDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<PerfilDomain, Long> {
}
