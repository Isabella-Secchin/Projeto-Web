import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './login.html',
  styleUrl: './login.css'
})
export class Login {

  email: string = '';
  senha: string = '';

  constructor(
    private router: Router,
    private authService: AuthService
  ) {}

  entrar(): void {

    this.authService.login(
      this.email,
      this.senha
    ).subscribe({

      next: (resposta) => {

        console.log('JWT recebido:', resposta.token);

        this.authService.salvarToken(
          resposta.token
        );

        alert('Login realizado com sucesso!');

        this.router.navigate(['/area-protegida']);
      },

      error: (erro) => {

        console.error(erro);

        alert('E-mail ou senha inválidos!');
      }
    });
  }

  criarConta(): void {
    this.router.navigate(['/cadastro']);
  }

  esqueciSenha(): void {
    console.log('Esqueci a senha');
  }
}