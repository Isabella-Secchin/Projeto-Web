import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface LoginResponse {
  token: string;
}

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private apiUrl = 'http://localhost:8080/auth';

  constructor(private http: HttpClient) {}

  login(email: string, senha: string): Observable<LoginResponse> {

    return this.http.post<LoginResponse>(
      `${this.apiUrl}/login`,
      {
        email: email,
        senha: senha
      }
    );
  }

  cadastrar(
    nome: string,
    email: string,
    senha: string
  ): Observable<string> {

    return this.http.post(
      `${this.apiUrl}/cadastro`,
      {
        nome: nome,
        email: email,
        senha: senha
      },
      {
        responseType: 'text'
      }
    );
  }

  testarRotaProtegida(): Observable<string> {
    return this.http.get('http://localhost:8080/teste', {responseType: 'text'});
  }

  salvarToken(token: string): void {
    localStorage.setItem('token', token);
  }

  pegarToken(): string | null {
    return localStorage.getItem('token');
  }

  logout(): void {
    localStorage.removeItem('token');
  }
}