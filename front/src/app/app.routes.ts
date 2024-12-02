import { Routes } from "@angular/router";
import { HomeComponent } from "./shared/features/home/home.component";
import {AuthGuard} from "./guards/auth.guard";
import {LoginComponent} from "./login/login.component";
import {ContactFormComponent} from "./contact/ui/contact-form/contact-form.component";
import {CartComponent} from "./cart/ui/cart-list/cart.component";
import { WishListComponent } from "./wishlist/ui/wishlist/wishlist.component";
import {RegisterComponent} from "./register/register.component";

export const APP_ROUTES: Routes = [
  {
    path: "home",
    component: HomeComponent,
  },
  {
    path: "products",
    canActivate: [AuthGuard],
    loadChildren: () =>
      import("./products/products.routes").then((m) => m.PRODUCTS_ROUTES)
  },
  {
    path: "contact",
    canActivate: [AuthGuard],
    component: ContactFormComponent,
  },
  {
    path: "cart",
    canActivate: [AuthGuard],
    component: CartComponent,
  },
  {
    path: "wishlist",
    canActivate: [AuthGuard],
    component: WishListComponent,
  },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path: "register",
    component: RegisterComponent
  },
  { path: "", redirectTo: "home", pathMatch: "full" },
];
