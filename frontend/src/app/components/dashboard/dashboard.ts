import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { Task } from '../../core/api/task/task';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { Observable, of } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  imports: [FormsModule, CommonModule],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.scss',
})
export class Dashboard implements OnInit {
  constructor(private task: Task, private cd: ChangeDetectorRef) {}
  taskDate!: string;
  formattedDate = '';
  tasksData: any[] = [];

  isModalOpen = false;
  isEditMode = false;
  editingTaskId: string | null = null;

  taskForm = {
    title: '',
    note: '',
    to: '',
    attachment: false,
    notificationDate: '',
  };

  ngOnInit(): void {
    this.setToday();
    this.fetchTasksByDate();
  }

  // ---------- Date ----------
  setToday() {
    const today = new Date();
    this.taskDate = today.toISOString().split('T')[0];
    this.formattedDate = `Selected: ${this.formatDateForApi(this.taskDate)}`;
  }

  changeDate(days: number) {
    const d = new Date(this.taskDate);
    d.setDate(d.getDate() + days);
    this.taskDate = d.toISOString().split('T')[0];
    this.fetchTasksByDate();
  }

  formatDateForApi(dateStr: string): string {
    const d = new Date(dateStr);
    return `${String(d.getDate()).padStart(2, '0')}-${String(d.getMonth() + 1).padStart(
      2,
      '0'
    )}-${d.getFullYear()}`;
  }

  formatDateDisplay(dateStr: string | null): string {
    if (!dateStr) return '-';
    const d = new Date(dateStr);
    return `${String(d.getDate()).padStart(2, '0')}-${String(d.getMonth() + 1).padStart(
      2,
      '0'
    )}-${d.getFullYear()}
     ${String(d.getHours()).padStart(2, '0')}:${String(d.getMinutes()).padStart(2, '0')}`;
  }

  // ---------- Modal ----------
  openModal() {
    this.isModalOpen = true;
  }

  closeModal() {
    this.clearForm();
    this.isEditMode = false;
    this.editingTaskId = null;
    this.isModalOpen = false;
  }

  clearForm() {
    this.taskForm = {
      title: '',
      note: '',
      to: '',
      attachment: false,
      notificationDate: '',
    };
  }

  // ---------- CRUD ----------
  fetchTasksByDate() {
    const formatted = this.formatDateForApi(this.taskDate);
    this.formattedDate = `Selected: ${formatted}`;
    this.task.getTask(formatted).subscribe({
      next: (task) => {
        this.tasksData = task;
        this.cd.detectChanges(); // @ToDo use async in html
      },
    });
  }

  saveTask() {
    const url = this.isEditMode ? `/task/update/${this.editingTaskId}` : '/task/create';

    const method = this.isEditMode ? 'put' : 'post';

    // (this.http as any)[method](url, this.taskForm).subscribe(() => {
    //   alert(this.isEditMode ? 'Task updated!' : 'Task created!');
    //   this.closeModal();
    //   this.fetchTasksByDate();
    // });
  }

  deleteTask(id: string) {
    // this.http.delete(`/task/delete/${id}`).subscribe(() => this.fetchTasksByDate());
  }

  editTask(task: any) {
    this.taskForm = {
      title: task.title,
      note: task.note,
      to: task.to,
      attachment: task.attachment,
      notificationDate: task.notificationDate
        ? new Date(task.notificationDate).toISOString().slice(0, 16)
        : '',
    };

    this.isEditMode = true;
    this.editingTaskId = task.id;
    this.isModalOpen = true;
  }
}
