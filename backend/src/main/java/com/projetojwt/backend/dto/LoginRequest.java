package com.projetojwt.backend.dto;

public record LoginRequest(
    String email,
    String senha
) {
}