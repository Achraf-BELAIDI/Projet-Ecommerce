package com.alten.ecommerce.service;


import com.alten.ecommerce.model.Wishlist;


public interface WishlistService {
  

    public Wishlist addOrRemoveProductToWishlist(String email, Long productId);

    public Wishlist getWishlist(String email); 
}
