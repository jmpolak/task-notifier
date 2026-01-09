import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  RouterStateSnapshot,
  Router,
  CanActivateChild,
} from '@angular/router';
import { TokenService } from '../services/token/token-service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivateChild {
  private loginInRoutes = ['/', '/sign-up']; // routes to skip

  constructor(private tokenService: TokenService, private router: Router) {}

  canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    const currentUrl = state.url;
    console.log(this.tokenService.isLoggedIn());
    // Check if user is logged in
    if (this.tokenService.isLoggedIn()) {
      if (this.loginInRoutes.includes(currentUrl)) {
        //this.router.navigate(['/dashboard']);
      }
      return true; // allow access
    }

    // Not logged in → redirect to login
    if (!this.loginInRoutes.includes(currentUrl)) this.router.navigate(['/']);
    return false;
  }
}
