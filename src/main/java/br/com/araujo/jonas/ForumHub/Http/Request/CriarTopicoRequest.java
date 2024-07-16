package br.com.araujo.jonas.ForumHub.Http.Request;

import br.com.araujo.jonas.ForumHub.Domain.CursoDomain;
import br.com.araujo.jonas.ForumHub.Domain.UsuarioDomain;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CriarTopicoRequest {
    @NotNull
    private String titulo;
    @NotNull
    private String mensagem;
    @NotNull
    private Long autor;
    @NotNull
    private Long curso;
}
