import { Injectable, inject, signal } from "@angular/core";
import { Product } from "../../products/data-access/product.model";
import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import { catchError, Observable, of, tap } from "rxjs";
import {SERVER_URL} from "../../config/constants";
import {AuthService} from "../../services/auth.service";
import { WishList } from "./wishlist.model";

@Injectable({
  providedIn: "root",

}) export class WishListService {
  wishListproducts = signal<Product[]> ([]);
  private readonly http = inject(HttpClient);
  private readonly path = SERVER_URL + "/api/wishlist";

  public addOrRemoveProductToWishList(product: Product): Observable<HttpResponse<WishList>> {
    return this.http.post<WishList>(`${this.path}/addOrRemove`, product, { observe: 'response'}).pipe(
      tap(()=>(this.getWishList().subscribe()))
    );
  }

  deleteFromWishList(productId: number): Observable<any> {
    return this.http.delete<any>(`http://localhost:8080/api/wishList/remove/${productId}`).pipe(
      tap(()=>(this.getWishList().subscribe()))
    );
  }


  public getWishList(): Observable<HttpResponse<WishList>> {
    return this.http.get<WishList>(`${this.path}/view`, { observe: 'response'}).pipe(
      tap(res => {
        this.wishListproducts.set( res.body?.products ?? []);
      })
    );;
  }


}
