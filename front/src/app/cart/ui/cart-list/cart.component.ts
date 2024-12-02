import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { CartService } from "../../data-access/cart.service";
import { CartItem } from "../../data-access/cart.model";
import { CommonModule, DecimalPipe, JsonPipe } from '@angular/common';
import {Router} from "@angular/router";
import {Product} from "../../../products/data-access/product.model";

@Component({
  selector: 'app-cart',
  standalone: true,
  imports: [
    CommonModule,
    JsonPipe,
    DecimalPipe
  ],
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  private readonly cartService = inject(CartService);
  private readonly cdRef = inject(ChangeDetectorRef);
  private readonly router = inject(Router);
  public cartItems: CartItem[] = [];

  ngOnInit(): void {
    this.loadCartItems();
  }

  private loadCartItems(): void {
    this.cartService.getCart().subscribe({
      next: (resp) => {
        if (resp.body) {
          this.cartItems = resp.body.items ?? [];
          this.cdRef.detectChanges();
        }
      },
      error: () => {
        console.error("Erreur de chargement des articles du panier");
      }
    });
  }

  trackById(index: number, item: CartItem): number {
    return item.product.id;
  }

  getTotalPrice(): number {
    return this.cartItems.reduce((total, item) => total + item.product.price * item.quantity, 0);
  }

  redirectToProducts(): void {
    this.router.navigate(['/products']); // Redirection vers '/products'
  }

  deleteFromCart(product: Product) {
    this.cartService.deleteFromCart(product.id).subscribe({
      next: ()=>{
        alert('deleted');
        this.loadCartItems()
      },
      error: ()=>{
        alert('error')
      }
    })
  }
}
