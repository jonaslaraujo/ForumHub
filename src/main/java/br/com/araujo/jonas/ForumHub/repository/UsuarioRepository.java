package br.com.araujo.jonas.ForumHub.repository;

import br.com.araujo.jonas.ForumHub.domain.UsuarioDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioDomain, Long> {
}
