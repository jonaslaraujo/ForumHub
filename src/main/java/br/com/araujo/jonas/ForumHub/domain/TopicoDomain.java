package br.com.araujo.jonas.ForumHub.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "topicos")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TopicoDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao;
    private String status;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private UsuarioDomain autor;
    @ManyToOne
    @JoinColumn(name = "curso_id")
    private CursoDomain curso;
    @ManyToMany
    @JoinColumn(name = "resposta_id")
    private RespostaDomain respostas;
}
