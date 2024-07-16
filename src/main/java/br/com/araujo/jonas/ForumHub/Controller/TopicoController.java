package br.com.araujo.jonas.ForumHub.Controller;

import br.com.araujo.jonas.ForumHub.Domain.TopicoDomain;
import br.com.araujo.jonas.ForumHub.Http.Request.CriarTopicoRequest;
import br.com.araujo.jonas.ForumHub.Service.TopicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
@AllArgsConstructor
public class TopicoController {
    private final TopicoService service;
    @PostMapping()
    public ResponseEntity<TopicoDomain> save(@RequestBody @Valid CriarTopicoRequest request) {
        var topico = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }
}

