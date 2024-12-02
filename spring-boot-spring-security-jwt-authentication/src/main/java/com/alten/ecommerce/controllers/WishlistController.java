package com.alten.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.Wishlist;
import com.alten.ecommerce.service.WishlistService;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;


    @PostMapping("/addOrRemove")
    public ResponseEntity<Wishlist> addProductToWishlist(@RequestBody Product product) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Wishlist result = wishlistService.addOrRemoveProductToWishlist(email, product.getId());
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/view")
    public ResponseEntity<?> getWishlist() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Wishlist wishlist = wishlistService.getWishlist(email);
        return ResponseEntity.ok(wishlist);
    }
}

