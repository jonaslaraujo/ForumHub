package br.com.araujo.jonas.ForumHub.Controller;

import br.com.araujo.jonas.ForumHub.Domain.TopicoDomain;
import br.com.araujo.jonas.ForumHub.Http.Request.CriarTopicoRequest;
import br.com.araujo.jonas.ForumHub.Service.TopicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/topicos")
@AllArgsConstructor
public class TopicoController {
    private final TopicoService service;
    @PostMapping()
    public ResponseEntity<TopicoDomain> criar(@RequestBody @Valid CriarTopicoRequest request) {
        var topico = service.criar(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(topico);
    }

    @GetMapping
    public void list() {}

    @PutMapping({"/id"})
    public ResponseEntity<TopicoDomain> atualizar(@PathVariable("id") Long id, @RequestBody @Valid CriarTopicoRequest request) {
        var topico = service.atualizar(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }
    @DeleteMapping({"/id"})
    public ResponseEntity<Object> excluir(@PathVariable("id") Long id) {
        service.excluir(id);
        return ResponseEntity.status(HttpStatus.OK).body("Tópico excluído.");
    }
}

