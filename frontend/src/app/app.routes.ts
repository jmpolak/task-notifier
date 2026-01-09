import { Routes } from '@angular/router';
import { Login } from './components/login/login';
import { Signup } from './components/signup/signup';
import { Dashboard } from './components/dashboard/dashboard';
import { AuthGuard } from './core/guard/auth-guard-guard';

export const routes: Routes = [
  {
    path: '',
    canActivateChild: [AuthGuard],
    children: [
      {
        path: '',
        component: Login,
      },
      {
        path: 'sign-up',
        component: Signup,
      },
      {
        path: 'dashboard',
        component: Dashboard,
      },
    ],
  },
];
