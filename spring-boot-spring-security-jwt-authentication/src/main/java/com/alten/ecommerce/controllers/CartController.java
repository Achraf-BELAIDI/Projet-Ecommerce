package com.alten.ecommerce.controllers;

import com.alten.ecommerce.model.CartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.service.CartService;

import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartItem> addProductToCart(@RequestBody Product product) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        CartItem cartItem = cartService.addProductToCart(email, product.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(cartItem);
    }

    @DeleteMapping("/remove/{productId}")
    public ResponseEntity<?> removeProductFromCart(@PathVariable Long productId) {
        System.out.println("ddddddddddddddd");
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        cartService.removeProductFromCart(email, productId);
        return ResponseEntity.noContent().build();

    }

    @GetMapping("/view")
    public ResponseEntity<?> getCart() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Cart cart = cartService.getCart(email);
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/quantity")
    public ResponseEntity<Integer> getCartQuantity() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Cart cart = cartService.getCart(email);
        Integer cartQuantity = cart.getItems().stream().mapToInt(CartItem::getQuantity).sum();
        return ResponseEntity.status(HttpStatus.OK).body(cartQuantity);
    }
}

