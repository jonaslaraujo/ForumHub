package br.com.araujo.jonas.ForumHub.Repository;

import br.com.araujo.jonas.ForumHub.Domain.UsuarioDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<UsuarioDomain, Long> {
}
