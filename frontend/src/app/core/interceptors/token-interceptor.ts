import { HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable, NgZone } from '@angular/core';
import { TokenService } from '../services/token/token-service';
import { catchError, EMPTY, throwError } from 'rxjs';
import { Router } from '@angular/router';

@Injectable()
export class TokenInterceptor implements HttpInterceptor {
  constructor(private tokenService: TokenService, private router: Router, private ngZone: NgZone) {}

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const token = this.tokenService.getToken();
    if (token) {
      req = req.clone({
        setHeaders: {
          Authorization: `Bearer ${token}`,
        },
      });
    }

    return next.handle(req).pipe(
      catchError((error: HttpErrorResponse) => {
        // Handle 401 Unauthorized
        console.log(error.status);
        if (error.status === 401) {
          // Redirect to login page
          this.tokenService.logout();
          this.router.navigate(['/']);
          return EMPTY;
        }

        // Optionally handle other status codes
        // if (error.status === 403) { ... }

        // Re-throw the error so the component can also handle it if needed
        return throwError(() => error);
      })
    );
  }
}
