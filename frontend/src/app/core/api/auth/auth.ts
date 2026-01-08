import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly url = 'api/user';
  constructor(private http: HttpClient) {}

  // Get all tasks
  // getAllTasks() {
  //   const taskArray: any[] = [];
  //   return this.http.get<any[]>(`${this.url}`);
  // }

  signUp(email: string, password: string) {
    return this.http.post<any[]>(`${this.url}/sign-up`, {
      email,
      password,
    });
  }
  login(email: string, password: string) {
    return this.http.post<any[]>(`${this.url}/login`, {
      email,
      password,
    });
  }
}
