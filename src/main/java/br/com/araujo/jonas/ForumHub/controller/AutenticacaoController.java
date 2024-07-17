package br.com.araujo.jonas.ForumHub.controller;

import br.com.araujo.jonas.ForumHub.domain.DataAuthentication;
import br.com.araujo.jonas.ForumHub.domain.UsuarioDomain;
import br.com.araujo.jonas.ForumHub.infra.security.DataTokenJWT;
import br.com.araujo.jonas.ForumHub.repository.UsuarioRepository;
import br.com.araujo.jonas.ForumHub.service.security.TokenService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DataAuthentication dados){
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.password());
        var autentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.createToken((UsuarioDomain) autentication.getPrincipal());

        return ResponseEntity.ok(new DataTokenJWT(tokenJWT));
    }
}
