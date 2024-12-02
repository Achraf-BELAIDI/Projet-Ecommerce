import { Injectable, inject, signal } from "@angular/core";
import { Product } from "../../products/data-access/product.model";
import {HttpClient, HttpResponse} from "@angular/common/http";
import { Observable, of, tap } from "rxjs";
import {SERVER_URL} from "../../config/constants";
import {AuthService} from "../../services/auth.service";
import {Cart, CartItem} from "./cart.model";

@Injectable({
  providedIn: "root",

}) export class CartService {
  cartQuantity = signal(0);
  private readonly http = inject(HttpClient);
  private readonly path = SERVER_URL + "/api/cart";

  public addToCart(product: Product): Observable<HttpResponse<CartItem>> {
    return this.http.post<CartItem>(`${this.path}/add`, product, { observe: 'response'}).pipe(
      tap(()=>(this.loadCartQuantity().subscribe()))
    );
  }

  deleteFromCart(productId: number): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/api/cart/remove/${productId}`).pipe(
      tap(()=>(this.loadCartQuantity().subscribe()))
    );
  }


  public getCart(): Observable<HttpResponse<Cart>> {
    return this.http.get<Cart>(`${this.path}/view`, { observe: 'response'});
  }

  loadCartQuantity(): Observable<number>{
    return this.http.get<number>(`${this.path}/quantity`, { observe: 'body'}).pipe(
      tap(res => {
        this.cartQuantity.set( res ?? 0);
      })
    );
  }

}
