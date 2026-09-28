import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-cadastro',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './cadastro.html',
  styleUrl: './cadastro.css'
})
export class Cadastro {

  nome: string = '';
  email: string = '';
  senha: string = '';
  confirmarSenha: string = '';

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  cadastrar(): void {

    if (
      !this.nome ||
      !this.email ||
      !this.senha ||
      !this.confirmarSenha
    ) {
      alert('Preencha todos os campos!');
      return;
    }

    if (this.senha !== this.confirmarSenha) {
      alert('As senhas não coincidem!');
      return;
    }

    this.authService.cadastrar(
      this.nome,
      this.email,
      this.senha
    ).subscribe({

      next: (resposta) => {

        console.log(resposta);

        alert('Usuário cadastrado com sucesso!');

        this.router.navigate(['/login']);
      },

      error: (erro) => {

        console.error(erro);

        alert(
          erro.error || 'Erro ao realizar cadastro.'
        );
      }
    });
  }

  voltarLogin(): void {
    this.router.navigate(['/login']);
  }
}