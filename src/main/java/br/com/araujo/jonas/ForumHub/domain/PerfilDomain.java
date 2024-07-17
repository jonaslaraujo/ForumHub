package br.com.araujo.jonas.ForumHub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Table(name = "perfil")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
}
