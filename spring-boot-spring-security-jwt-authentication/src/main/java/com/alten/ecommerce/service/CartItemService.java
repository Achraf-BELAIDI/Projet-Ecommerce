/**package com.alten.ecommerce.service;

import com.alten.ecommerce.model.CartItem;


import java.util.List;

public interface CartItemService {
    public List<CartItem> getItemsByCart(Long cartId);

    public void updateCartItemQuantity(Long cartItemId, int quantity);

    public void deleteCartItem(Long cartItemId);
}**/

package com.alten.ecommerce.service;

import com.alten.ecommerce.model.CartItem;

import java.util.List;

public interface CartItemService {

    List<CartItem> getItemsByCart(Long cartId);

    void updateCartItemQuantity(Long cartItemId, int quantity);

    void deleteCartItem(Long cartItemId);
}

