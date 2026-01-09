import { Injectable } from '@angular/core';
import { Auth } from '../../api/auth/auth';
import { TokenService } from '../../services/token/token-service';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthFacade {
  constructor(private authApi: Auth, private tokenService: TokenService, private router: Router) {}

  public authUser(email: string, password: string, login: boolean) {
    const apiFunc = login
      ? this.authApi.login.bind(this.authApi)
      : this.authApi.signUp.bind(this.authApi);
    apiFunc(email, password).subscribe({
      next: (res) => {
        if (res.success) {
          this.tokenService.saveToken(res.token);
          this.router.navigate(['dashboard']);
        } else {
          console.log('Wrong Pwd');
        }
      },
      error: (err) => console.log(err),
    });
  }
}
