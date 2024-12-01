package com.ArcaDeLaAlianza.ArcaDeLaAlianza.controller;

import com.ArcaDeLaAlianza.ArcaDeLaAlianza.domain.CartItem;
import com.ArcaDeLaAlianza.ArcaDeLaAlianza.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carrito")
public class CarritoController {

    @Autowired
    private CarritoService cartService;


    @GetMapping
    public List<CartItem> getCartItems() {
        return cartService.getCartItems();
    }


    @PostMapping("/add")
    public void addToCart(@RequestBody CartItem cartItem) {
        cartService.addToCart(cartItem.getProductId(), cartItem.getName(), cartItem.getPrice(), cartItem.getQuantity());
    }


    @DeleteMapping("/remove/{productId}")
    public void removeFromCart(@PathVariable String productId) {
        cartService.removeFromCart(productId);
    }


    @DeleteMapping("/clear")
    public void clearCart() {
        cartService.clearCart();
    }


    @GetMapping("/total")
    public Double getTotal() {
        return cartService.getTotal();
    }
}
