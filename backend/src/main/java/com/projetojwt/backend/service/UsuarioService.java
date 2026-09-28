package com.projetojwt.backend.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.projetojwt.backend.dto.CadastroRequest;
import com.projetojwt.backend.dto.LoginRequest;
import com.projetojwt.backend.entity.Usuario;
import com.projetojwt.backend.repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioService(
        UsuarioRepository usuarioRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Usuario cadastrar(CadastroRequest request) {

        if (usuarioRepository.existsByEmail(request.email())) {
            throw new RuntimeException("E-mail já cadastrado");
        }

        String senhaCriptografada =
            passwordEncoder.encode(request.senha());

        Usuario usuario = new Usuario(
            request.nome(),
            request.email(),
            senhaCriptografada
        );


        return usuarioRepository.save(usuario);
    }

    public Usuario autenticar(LoginRequest request) {

        Usuario usuario = usuarioRepository
            .findByEmail(request.email())
            .orElseThrow(() ->
                new RuntimeException("E-mail ou senha inválidos")
            );

        boolean senhaCorreta = passwordEncoder.matches(
            request.senha(),
            usuario.getSenha()
        );

        if (!senhaCorreta) {
            throw new RuntimeException("E-mail ou senha inválidos");
        }

        return usuario;
}
}