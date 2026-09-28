import { Routes } from '@angular/router';

import { Login } from './login/login';
import { Cadastro } from './cadastro/cadastro';
import { AreaProtegida } from './area-protegida/area-protegida';

import { authGuard } from './guards/auth.guard';

export const routes: Routes = [

  {
    path: '',
    redirectTo: 'login',
    pathMatch: 'full'
  },

  {
    path: 'login',
    component: Login
  },

  {
    path: 'cadastro',
    component: Cadastro
  },

  {
    path: 'area-protegida',
    component: AreaProtegida,
    canActivate: [authGuard]
  }

];