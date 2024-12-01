package com.ArcaDeLaAlianza.ArcaDeLaAlianza.service;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.CartItem;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarritoService {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(String productId, String name, Double price, Integer quantity) {
        for (CartItem item : cartItems) {
            if (item.getProductId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        cartItems.add(new CartItem(productId, name, price, quantity));
    }

    public void removeFromCart(String productId) {
        cartItems.removeIf(item -> item.getProductId().equals(productId));
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void clearCart() {
        cartItems.clear();
    }

    public Double getTotal() {
        return cartItems.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}
