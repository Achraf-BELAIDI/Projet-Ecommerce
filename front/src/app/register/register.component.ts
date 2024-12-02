import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AuthService } from '../services/auth.service'; // Service pour l'inscription
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss'],
  standalone: true,
  imports: [
    FormsModule,
    ReactiveFormsModule,
    CommonModule
  ],
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  confirmPassword = '';
  aboutMe = '';
  authError = false;

  constructor(private authService: AuthService, private router: Router) {}

  register() {
    if (this.password !== this.confirmPassword) {
      this.authError = true;
      return;
    }

    const user = {
      name: this.name,
      email: this.email,
      password: this.password,
      aboutMe: this.aboutMe
    };

    // Appelez le service d'inscription pour envoyer les données
    this.authService.register(user).subscribe(
      () => {
        // Redirigez vers la page d'accueil après une inscription réussie
        this.router.navigate(['/']);
      },
      (error: any) => {
        console.error('Registration failed', error);
        this.authError = true;
      }
    );
  }
}
