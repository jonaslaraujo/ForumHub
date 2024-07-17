package br.com.araujo.jonas.ForumHub.repository;

import br.com.araujo.jonas.ForumHub.domain.UsuarioDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioDomain, Long> {
    UserDetails findByLogin(String username);
}
