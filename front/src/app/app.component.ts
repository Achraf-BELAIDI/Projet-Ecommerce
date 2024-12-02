import {Component, inject} from '@angular/core';
import { Router } from '@angular/router';
import { RouterModule } from '@angular/router';
import { SplitterModule } from 'primeng/splitter';
import { ToolbarModule } from 'primeng/toolbar';
import { PanelMenuComponent } from './shared/ui/panel-menu/panel-menu.component';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { AuthInterceptor } from './interceptors/auth.interceptor';
import { AuthService } from './services/auth.service';
import {CommonModule} from "@angular/common";
import {CartService} from "./cart/data-access/cart.service";
import { WishListService } from './wishlist/data-access/wishlist.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss'],
  standalone: true,
  imports: [RouterModule, SplitterModule, ToolbarModule, PanelMenuComponent, CommonModule],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true,
    },
  ],
})
export class AppComponent {
  title = 'ALTEN SHOP';

  public cartService = inject(CartService);
  public wishlistService = inject(WishListService);
  constructor(public authService: AuthService, private router: Router) {}

  ngOnInit() {
    // Vérification du token au moment où le composant est initialisé
    if (!this.authService.getToken()) {
      this.router.navigate(['/login']); // Redirige vers la page de login si le token est absent
    }
    this.cartService.loadCartQuantity().subscribe();
    this.wishlistService.getWishList().subscribe();
  }
  logout() {
    this.authService.logout();
    this.router.navigate(['/login']); // Redirige vers la page de connexion après déconnexion
  }

  isAuthenticated(): boolean {
    return !!this.authService.getToken(); // Retourne true si le token existe
  }
}
