import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class Auth {
  private readonly url = 'api/task/all';
  constructor(private http: HttpClient) {}

  // Get all tasks
  getAllTasks() {
    const taskArray: any[] = [];
    return this.http.get<any[]>(`${this.url}`);
  }
}
