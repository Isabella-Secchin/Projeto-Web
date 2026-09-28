package com.projetojwt.backend.dto;

public record CadastroRequest(
    String nome,
    String email,
    String senha
) {
}