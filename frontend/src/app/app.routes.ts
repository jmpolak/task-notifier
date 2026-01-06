import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Signup } from './components/signup/signup';

export const routes: Routes = [
  {
    path: '',
    component: Login,
  },
  {
    path: 'sign-up',
    component: Signup,
  },
];
