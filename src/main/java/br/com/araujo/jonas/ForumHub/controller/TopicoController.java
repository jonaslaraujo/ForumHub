package br.com.araujo.jonas.ForumHub.controller;

import br.com.araujo.jonas.ForumHub.domain.TopicoDomain;
import br.com.araujo.jonas.ForumHub.http.request.CriarTopicoRequest;
import br.com.araujo.jonas.ForumHub.service.TopicoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<TopicoDomain>> listAll() {
        var listTopicos = service.listarTodos();
        return ResponseEntity.status(HttpStatus.OK).body(listTopicos);
    }

    @GetMapping("/paged")
    public Page<TopicoDomain> listPaged(@RequestParam(defaultValue = "0") int page,
                                        @RequestParam(defaultValue = "10") int size) {
        return service.listarPaginado(page, size);
    }

    @GetMapping("/byfilter")
    public Page<TopicoDomain> listByFilter(@RequestParam String nomeCurso,
                                           @RequestParam int ano,
                                           @RequestParam(defaultValue = "0") int page,
                                           @RequestParam(defaultValue = "10") int size) {
        return service.listarPorCursoEAno(nomeCurso, ano, page, size);
    }

    @GetMapping({"/id"})
    public void detailTopics(@PathVariable("id") Long id) {

    }

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

