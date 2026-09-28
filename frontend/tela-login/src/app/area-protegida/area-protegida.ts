import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AuthService } from '../services/auth.service';

@Component({
  selector: 'app-area-protegida',
  standalone: true,
  imports: [],
  templateUrl: './area-protegida.html',
  styleUrl: './area-protegida.css'
})
export class AreaProtegida implements OnInit {

  mensagem: string = 'Carregando...';

  constructor(
    private authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {

    this.authService.testarRotaProtegida().subscribe({

      next: (resposta) => {
        this.mensagem = resposta;
      },

      error: (erro) => {
        console.error(erro);

        this.authService.logout();

        this.router.navigate(['/login']);
      }
    });
  }

  sair(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}