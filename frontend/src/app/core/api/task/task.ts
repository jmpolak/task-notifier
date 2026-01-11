import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

interface ResponseTaskDto {
  id: string; // optional - from db
  title: string;
  note: string;
  to: string;
  attachment: boolean;
  completed: boolean;
  notificationDate: string;
  createDate: string;
}
@Injectable({
  providedIn: 'root',
})
export class Task {
  private readonly url = 'api/task';
  constructor(private http: HttpClient) {}

  getTask(date: string) {
    return this.http.get<ResponseTaskDto[]>(`${this.url}/by-date?date=${date}`);
  }
}
