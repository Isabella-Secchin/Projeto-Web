package com.projetojwt.backend.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TesteController {

    @GetMapping("/teste")
    public String teste() {
        return "Você acessou uma rota protegida!";
    }
}