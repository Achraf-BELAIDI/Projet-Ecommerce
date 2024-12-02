import { Component, inject, OnInit, ChangeDetectorRef } from '@angular/core';
import { WishListService } from "../../data-access/wishlist.service";
import { CommonModule } from '@angular/common';
import {Router} from "@angular/router";
import { WishList } from 'app/wishlist/data-access/wishlist.model';
import { Product } from 'app/products/data-access/product.model';

@Component({
  selector: 'app-wishList',
  standalone: true,
  imports: [
    CommonModule,
  ],
  templateUrl: './wishlist.component.html',
  styleUrls: ['./wishlist.component.css']
})
export class WishListComponent {
  public wishListService = inject(WishListService);
  private readonly cdRef = inject(ChangeDetectorRef);
  private readonly router = inject(Router);

  trackById(index: number, product: Product): number {
    return product.id;

  }

  redirectToProducts(): void {
    this.router.navigate(['/products']);
  }

   deleteFromWishList(product: Product) {
    this.wishListService.addOrRemoveProductToWishList(product).subscribe()
  }
}
