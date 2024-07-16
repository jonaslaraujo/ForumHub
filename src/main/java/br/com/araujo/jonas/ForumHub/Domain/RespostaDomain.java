package br.com.araujo.jonas.ForumHub.Domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "resposta")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RespostaDomain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensagem;
    @ManyToMany
    @JoinColumn(name = "topico_id")
    private TopicoDomain topico;
    private Date dataCriacao;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private UsuarioDomain autor;
    private String solucao;
}
