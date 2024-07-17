package br.com.araujo.jonas.ForumHub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Table(name = "usuario")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String login;
    private String email;
    private String senha;
    @OneToMany
    @JoinColumn(name = "perfil_id")
    private List<PerfilDomain> perfis;
}
