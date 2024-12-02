package com.alten.ecommerce.service;

import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.CartItem;

public interface CartService {

    CartItem addProductToCart(String email, Long productId);

    void removeProductFromCart(String email, Long productId);

    Cart getCart(String email);
}




