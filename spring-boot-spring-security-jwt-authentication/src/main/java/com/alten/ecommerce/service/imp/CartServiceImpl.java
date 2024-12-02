/**package com.alten.ecommerce.service.imp;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.UserEntity;
import com.alten.ecommerce.repository.CartRepository;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.service.CartService;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    private ProductRepository productRepository;

    private UserRepository userRepository;

    @Override
    public void addProductToCart(String email, Long productId, int quantity) {
        // Récupérer l'utilisateur à partir de l'email
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Vérifier que le produit existe
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Récupérer ou créer un panier pour l'utilisateur
        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> new Cart(user));

        // Ajouter ou mettre à jour l'item dans le panier
        cart.addItem(product, quantity);

        // Enregistrer le panier mis à jour
        cartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(String email, Long productId) {
        // Récupérer l'utilisateur à partir de l'email
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Récupérer le panier associé à l'utilisateur
        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        // Supprimer l'élément correspondant au produit du panier
        cart.removeItem(productId);

        // Enregistrer les modifications dans le panier
        cartRepository.save(cart);
    }

    @Override
    public Cart getCart(String email) {
        // Récupérer l'utilisateur à partir de l'email
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Retourner le panier de l'utilisateur ou une exception s'il n'existe pas
        return cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
    }
}**/

package com.alten.ecommerce.service.imp;

import com.alten.ecommerce.model.CartItem;
import com.alten.ecommerce.repository.CartItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alten.ecommerce.model.Cart;
import com.alten.ecommerce.model.Product;
import com.alten.ecommerce.model.UserEntity;
import com.alten.ecommerce.repository.CartRepository;
import com.alten.ecommerce.repository.ProductRepository;
import com.alten.ecommerce.repository.UserRepository;
import com.alten.ecommerce.service.CartService;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;


    public CartItem addProductToCart(String email, Long productId) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        System.out.println("User: " + user.getId() + " - " + user.getEmail());

        Optional<CartItem> optionalCartItem = cartItemRepository.findByProductIdAndCartUserEmail(product.getId(), email);
        if(optionalCartItem.isPresent()){
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + 1);
           return cartItemRepository.save(cartItem);
        }
        else{

            Cart cart = cartRepository.findByUser(user)
                    .orElseGet(() -> {
                        Cart newCart = new Cart(user);
                        cartRepository.save(newCart);
                        return newCart;
                    });

            cart.addItem(product, 1);
            Cart result = cartRepository.save(cart);
            return result.getItems().get(0);
        }
    }

    @Override
    public void removeProductFromCart(String email, Long productId) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart not found"));
        cart.removeItem(productId);
        cartRepository.save(cart);
    }

    @Override
    public Cart getCart(String email) {

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart(user);
                    return cartRepository.save(newCart);
                });
    }
}

