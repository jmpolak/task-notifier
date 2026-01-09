import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface ResponseDto {
  success: boolean;
  token: string;
}
@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly url = 'api/user';
  constructor(private http: HttpClient) {}

  signUp(email: string, password: string) {
    return this.http.post<ResponseDto>(`${this.url}/sign-up`, {
      email,
      password,
    });
  }
  login(email: string, password: string) {
    return this.http.post<ResponseDto>(`${this.url}/login`, {
      email,
      password,
    });
  }
}
