package com.alten.ecommerce.service.imp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.UserEntity;
import com.alten.ecommerce.model.Wishlist;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.repository.WishlistRepository;
import com.alten.ecommerce.service.WishlistService;

@Service
@Transactional
public class WishlistServiceImpl implements WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;


    @Override
    public Wishlist addOrRemoveProductToWishlist(String email, Long productId) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Récupération ou création d'une liste d'envies vide
        Wishlist wishlist = wishlistRepository.findByUser(user)
                .orElseGet(() -> new Wishlist(user));

        // Vérification si le produit est déjà dans la liste
        if (!wishlist.getProducts().contains(product)) {
            wishlist.addItem(product);
            return wishlistRepository.save(wishlist);
        } else {
        	wishlist.removeItem(productId);
            return wishlistRepository.save(wishlist);
        }
    }


    @Override
    public Wishlist getWishlist(String email) {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return wishlistRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Wishlist not found"));
    }
}
