import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { SERVER_URL } from "../config/constants";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private signinApiUrl = SERVER_URL + "/auth/login";
  private registerApiUrl = SERVER_URL + "/user/register";

  constructor(private http: HttpClient) {}

  login(email: string, password: string): Observable<any> {
    return this.http.post(this.signinApiUrl, {
      "email": email,
      "password": password
    }).pipe(
      tap((response: any) => {
        if (response.token) {
          this.setToken(response.token);
          console.log('Token stored in sessionStorage:', response.token); // Verification
        }
      }),
      catchError((error) => {
        // Handle errors from the backend
        return throwError(() => new Error(error.error.message || 'Login failed'));
      })
    );
  }
  register(user: any): Observable<any> {  // Méthode pour inscrire un utilisateur
    return this.http.post(this.registerApiUrl, user).pipe(
      tap((response: any) => {
        console.log('User registered successfully:', response);
      }),
      catchError((error) => {
        // Gérer les erreurs d'inscription
        return throwError(() => new Error(error.error.message || 'Registration failed'));
      })
    );
  }

  getToken(): string | null {
    return sessionStorage.getItem('jwt_token');
  }

  setToken(token: string): void {
    sessionStorage.setItem('jwt_token', token);
  }

  logout(): void {
    sessionStorage.removeItem('jwt_token');
  }

}
