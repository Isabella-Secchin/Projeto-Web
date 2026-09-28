package com.projetojwt.backend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetojwt.backend.dto.CadastroRequest;
import com.projetojwt.backend.dto.LoginRequest;
import com.projetojwt.backend.dto.LoginResponse;
import com.projetojwt.backend.entity.Usuario;
import com.projetojwt.backend.service.JwtService;
import com.projetojwt.backend.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtService jwtService;

    public AuthController(
        UsuarioService usuarioService,
        JwtService jwtService
    ) {
        this.usuarioService = usuarioService;
        this.jwtService = jwtService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<?> cadastrar(
        @RequestBody CadastroRequest request
    ) {

        try {

            Usuario usuario = usuarioService.cadastrar(request);

            return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("Usuário cadastrado com sucesso. ID: " + usuario.getId());

        } catch (RuntimeException e) {

            return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
        @RequestBody LoginRequest request
    ) {

        try {

            Usuario usuario = usuarioService.autenticar(request);

            String token = jwtService.gerarToken(usuario);

            return ResponseEntity.ok(
                new LoginResponse(token)
            );

        } catch (RuntimeException e) {

            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
        }
    }
}