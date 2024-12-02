import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ButtonModule } from 'primeng/button';
import { InputTextModule } from 'primeng/inputtext';
import { InputNumberModule } from 'primeng/inputnumber';
import { InputTextareaModule } from 'primeng/inputtextarea';
import { DropdownModule } from 'primeng/dropdown';
import { AuthService } from '../services/auth.service';
import { PasswordModule } from 'primeng/password';
import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  standalone: true,
  imports: [
    FormsModule,
    ButtonModule,
    InputTextModule,
    InputNumberModule,
    InputTextareaModule,
    ReactiveFormsModule,
    PasswordModule,
    DropdownModule,
    CommonModule
  ],
})
export class LoginComponent {
  email = '';
  password = '';
  authError = false;

  constructor(private authService: AuthService, private router: Router) {}

  login() {
    this.authService.login(this.email, this.password).subscribe(
      () => {
        this.router.navigate(['/']);
      },
      (error: any) => {
        console.error('Login failed', error);
        this.authError = true;
      }
    );
  }
  goToRegister() {
    // Redirection vers la page d'inscription
    this.router.navigate(['/register']);
  }
}
